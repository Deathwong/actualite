package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.dto.FiltreDto;
import com.jeff.actualite.domain.dto.HabilitationDto;
import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Filtre;
import com.jeff.actualite.domain.entity.Habilitation;
import com.jeff.actualite.domain.entity.Section;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.domain.mapper.FiltreMapper;
import com.jeff.actualite.domain.mapper.HabilitationMapper;
import com.jeff.actualite.domain.mapper.SectionMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.repository.FiltreRepository;
import com.jeff.actualite.repository.HabilitationRepository;
import com.jeff.actualite.repository.SectionRepository;
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
    private final ActualiteMapper actualiteMapper;
    private final HabilitationMapper habilitationMapper;
    private final FiltreMapper filtreMapper;
    private final SectionMapper sectionMapper;

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
        List<Section> sections = sectionDtos
                .stream()
                .map(sectionDto -> sectionMapper.map(sectionDto, actualite))
                .toList();
        sectionRepository.saveAll(sections);

        return actualite.getId();
    }
}