package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.dto.RessourceDto;
import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Image;
import com.jeff.actualite.domain.entity.Ressource;
import com.jeff.actualite.domain.entity.Section;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.domain.mapper.ImageMapper;
import com.jeff.actualite.domain.mapper.RessourceMapper;
import com.jeff.actualite.repository.*;
import com.jeff.actualite.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActualiteServiceImpl implements ActualiteService {

    private final ActualiteRepository actualiteRepository;
    private final ActualiteMapper actualiteMapper;
    private final HabilitationService habilitationService;
    private final FiltreService filtreService;
    private final SectionService sectionService;
    private final ImageRepository imageRepository;
    private final ResourceRepository resourceRepository;
    private final ImageMapper imageMapper;
    private final RessourceMapper ressourceMapper;
    private final FiltreRepository filtreRepository;
    private final HabilitationRepository habilitationRepository;
    private final SectionRepository sectionRepository;
    private final ImageService existeImage;

    @Override
    public ActualiteDto consulter(Long id) {

        Actualite actualite = actualiteRepository.findActualiteByIdAndActiveTrue(id);

        return actualiteMapper.map(actualite);
    }

    @Override
    public Long creer(ActualiteDto actualiteDto) {

        // Actualité
        Actualite actualite = actualiteMapper.map(actualiteDto);
        Actualite actualiteBdd = actualiteRepository.save(actualite);

        // Habilitation
        habilitationService.saveAll(actualiteDto.getHabilitations(), actualiteBdd);

        // Filtres
        filtreService.saveAll(actualiteDto.getFiltres(), actualite);

        // Section
        List<Image> images = new ArrayList<>();
        List<Ressource> ressources = new ArrayList<>();
        List<SectionDto> sectionDtos = actualiteDto.getSections();

        sectionDtos.forEach(sectionDto -> {
            // Enregistrement de la Section
            Section sectionBdd = sectionService.save(sectionDto, actualite);

            // Image
            if (sectionDto.getImage() != null) {
                Image image = imageMapper.map(sectionDto.getImage(), sectionBdd);
                images.add(image);
            }

            // Ressources
            if (sectionDto.getRessources() != null) {
                ressources.addAll(sectionDto.getRessources()
                        .stream()
                        .map(ressourceDto -> ressourceMapper.map(ressourceDto, sectionBdd))
                        .toList()
                );
            }
        });

        // Enregistrement de l'image
        if (!CollectionUtils.isEmpty(images)) {
            imageRepository.saveAll(images);
        }

        // Enregistrement des ressources
        if (!CollectionUtils.isEmpty(ressources)) {
            resourceRepository.saveAll(ressources);
        }

        return actualite.getId();
    }

    @Override
    public List<ActualiteDto> rechercherWithDateCreation(String dateCreation) {

        Instant date = Instant.parse(dateCreation);

        List<Actualite> actualites = actualiteRepository.findAll(ActualiteRepository.hasDateCreation(date));

        return actualites.stream()
                .map(actualiteMapper::map)
                .toList();
    }

    @Override
    public Page<ActualiteDto> rechercherWithPageable(Pageable pageable, String titre, Instant dateCreation) {

        Page<Actualite> actualitePage = actualiteRepository.findAll(pageable);

        List<ActualiteDto> actualiteDtos = actualitePage.stream().
                map(actualiteMapper::map)
                .toList();
        return new PageImpl<>(actualiteDtos);
    }

    @Override
    public List<ActualiteDto> rechercher(String titre) {

        List<Actualite> actualites = actualiteRepository.findAll(ActualiteRepository.hasTitre(titre));

        return actualites.stream()
                .map(actualiteMapper::map)
                .toList();
    }

    @Override
    @Transactional
    public void modifier(ActualiteDto actualiteDto, Long id) {

        //S003-TX001-RG001
        //Créer l’en-tête de l’actualité à modifier
        //S003-TX001-RG001
        //mettre à jour la ligne dans la table ACTUALITE
        Actualite actualite = actualiteMapper.map(actualiteDto);
        actualite.setId(id);
        Instant dateCreation = actualiteRepository.findById(id).get().getDateCreation();
        actualite.setDateCreation(dateCreation);
        Actualite actualiteBdd = actualiteRepository.save(actualite);

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
            if (existeImage.existeImage(idImage)) {
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

    @Override
    @Transactional
    public void supprimer(Long id) {

        //Activité n°1 - Supprimer la totalité d’une actualité
        Long idImage = imageRepository.getIdImageByIdActualite(id);
        if (idImage != null) {
            if (existeImage.existeImage(idImage)) {
                imageRepository.deleteAllByIdActualite(id);
            }
        }

        //S004-TX001-RG002
        //Supprimer l’ensemble des ressources
        resourceRepository.deleteAllByIdActualite(id);

        //S004-TX001-RG003
        //Supprimer l’ensemble des sections
        sectionRepository.deleteAllByIdActualite(id);

        //S004-TX001-RG004
        //Supprimer l’ensemble des filtres
        filtreRepository.deleteAllByIdActualite(id);

        //S004-TX001-RG005
        //Supprimer l’ensemble des habilitations
        habilitationRepository.deleteAllByIdActualite(id);

        //S004-TX001-RG006
        //Supprimer l’actualité
        actualiteRepository.deleteById(id);

    }

    @Override
    public boolean existActualite(Long actualiteId) {

        Optional<Actualite> actualite = actualiteRepository.findById(actualiteId);

        return actualite.isEmpty();
    }
}
