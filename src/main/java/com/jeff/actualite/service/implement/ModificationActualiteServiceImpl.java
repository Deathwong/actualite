package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.dto.RessourceDto;
import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Image;
import com.jeff.actualite.domain.entity.Ressource;
import com.jeff.actualite.domain.entity.Section;
import com.jeff.actualite.domain.mapper.ImageMapper;
import com.jeff.actualite.domain.mapper.RessourceMapper;
import com.jeff.actualite.repository.*;
import com.jeff.actualite.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ModificationActualiteServiceImpl implements ModificationActualiteService {

    private final FiltreRepository filtreRepository;
    private final HabilitationRepository habilitationRepository;
    private final SectionRepository sectionRepository;
    private final ImageRepository imageRepository;
    private final ResourceRepository resourceRepository;
    private final HabilitationService habilitationService;
    private final SectionService sectionService;
    private final FiltreService filtreService;
    private final ActualiteService actualiteService;
    private final ImageMapper imageMapper;
    private final RessourceMapper ressourceMapper;
    private final VerificationExistenceService verificationExistenceService;

    @Override
    public void modifier(ActualiteDto actualiteDto, Long id) {

        //S003-TX001-RG001
        //Créer l’en-tête de l’actualité à modifier
        //S003-TX001-RG001
        //mettre à jour la ligne dans la table ACTUALITE
        Actualite actualiteBdd = actualiteService.save(actualiteDto, id);

        //S003-TX001-RG002
        //supprimer l'ensemble des filtres
        filtreRepository.deleteAllByIdActualite(id);

        //S003-TX001-RG003
        //Supprimer l’ensemble des habilitations
        habilitationRepository.deleteAllByIdActualite(id);

        //S003-TX001-RG004
        // Habilitation
        habilitationService.saveAll(actualiteDto.getHabilitations(), actualiteBdd);

        //S003-TX001-RG004
        // Filtres
        filtreService.saveAll(actualiteDto.getFiltres(), actualiteBdd);

        //Créer les sections

        List<Section> sections = sectionRepository.findAllByIdActualite(id);
        List<Image> images = new ArrayList<>();
        List<Ressource> ressources = new ArrayList<>();

        sections.forEach(section -> {

            //S003-TX001-RG006
            //delete image
            if (section.getImage() != null) {
                imageRepository.deleteById(section.getId());
            }

            //S003-TX001-RG007
            //delete ressource
            List<Ressource> ressourcesToDelete = resourceRepository.findAllByIdSection(section.getId());
            ressourcesToDelete.forEach(ressource ->
                    resourceRepository.deleteById(ressource.getId())
            );

            //S003-TX001-RG008
            //delete sections
            sectionRepository.deleteById(section.getId());
        });

        //S003-TX001-RG009
        //Créer les sections
        List<SectionDto> sectionDtos = actualiteDto.getSections();
        sectionDtos.forEach(sectionDto -> {

            // Sauvegarde des sections
            Section section = sectionService.save(sectionDto, actualiteBdd);

            if (sectionDto.getImage() != null) {
                Image image = imageMapper.map(sectionDto.getImage(), section);
                images.add(image);
            }

            if (sectionDto.getRessources() != null) {
                List<RessourceDto> ressourcesDto = sectionDto.getRessources();
                ressources.addAll(ressourcesDto
                        .stream()
                        .map(ressourceDto -> ressourceMapper.map(ressourceDto, section))
                        .toList()
                );
            }
        });

        //S003-TX001-RG010
        //Activité n°3 – Créer l’image associée à chaque section
        //save images
        Long idImage = imageRepository.getIdImageByIdActualite(actualiteBdd.getId());
        if (idImage != null) {
            if (verificationExistenceService.existeImage(idImage)) {
                if (!CollectionUtils.isEmpty(images)) {
                    imageRepository.saveAll(images);
                }
            }
        }

        //Activité n°4 – Créer le(s) ressource(s) associée à chaque section
        //S003-TX001-RG011
        //save ressources
        if (!CollectionUtils.isEmpty(ressources)) {
            resourceRepository.saveAll(ressources);
        }

    }
}
