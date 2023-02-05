package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Image;
import com.jeff.actualite.domain.entity.Ressource;
import com.jeff.actualite.domain.entity.Section;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.domain.mapper.ImageMapper;
import com.jeff.actualite.domain.mapper.RessourceMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.repository.ImageRepository;
import com.jeff.actualite.repository.ResourceRepository;
import com.jeff.actualite.service.CreationActualiteService;
import com.jeff.actualite.service.FiltreService;
import com.jeff.actualite.service.HabilitationService;
import com.jeff.actualite.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreationActualiteServiceIpml implements CreationActualiteService {

    private final HabilitationService habilitationService;
    private final FiltreService filtreService;
    private final SectionService sectionService;
    private final ActualiteRepository actualiteRepository;
    private final ImageRepository imageRepository;
    private final ResourceRepository resourceRepository;
    private final ActualiteMapper actualiteMapper;
    private final ImageMapper imageMapper;
    private final RessourceMapper ressourceMapper;

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
}