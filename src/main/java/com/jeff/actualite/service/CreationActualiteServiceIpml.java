package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.*;
import com.jeff.actualite.domain.entity.*;
import com.jeff.actualite.domain.mapper.*;
import com.jeff.actualite.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CreationActualiteServiceIpml implements CreationActualiteService {

    private final ActualiteRepository actualiteRepository;
    private final HabilitationRepository habilitationRepository;
    private final FiltreRepository filtreRepository;
    private final SectionRepository sectionRepository;
    private final ImageRepository imageRepository;
    private final ActualiteMapper actualiteMapper;
    private final HabilitationMapper habilitationMapper;
    private final FiltreMapper filtreMapper;
    private final SectionMapper sectionMapper;
    private final ImageMapper imageMapper;

    @Override
    public Long creer(ActualiteDto actualiteDto) {
        // Créer l’en-tête de l’actualité
        // RGOO1 Actualité
        Actualite actualite = actualiteMapper.map(actualiteDto);
        Actualite actualiteSave = actualiteRepository.save(actualite);

        // Habilitation
        if (!CollectionUtils.isEmpty(actualiteDto.getHabilitations())) {
            List<HabilitationDto> habilitationDtos = actualiteDto.getHabilitations();
            List<Habilitation> habilitations = habilitationDtos
                    .stream()
                    .map(habilitationDto -> habilitationMapper.map(habilitationDto, actualiteSave))
                    .toList();
            habilitationRepository.saveAll(habilitations);
        }

        // Filtres
        if (!CollectionUtils.isEmpty(actualiteDto.getFiltres())) {
            List<FiltreDto> filtreDtos = actualiteDto.getFiltres();
            List<Filtre> filtres = filtreDtos
                    .stream()
                    .map(filtreDto -> filtreMapper.map(filtreDto, actualite))
                    .toList();
            filtreRepository.saveAll(filtres);
        }

        // Sections
        List<SectionDto> sectionDtos = actualiteDto.getSections();

        for (SectionDto sectionDto : sectionDtos) {

            Section section = sectionMapper.map(sectionDto, actualite);
            section = sectionRepository.save(section);

            // Images
            if (sectionDto.getImage() != null) {
                ImageDto imageDto = sectionDto.getImage();
                Image image = imageMapper.map(imageDto, section);
                imageRepository.save(image);
            }

        }

        return actualite.getId();
    }
}