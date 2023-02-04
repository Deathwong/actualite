package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.dto.HabilitationDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Habilitation;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.domain.mapper.HabilitationMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.repository.HabilitationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CreationActualiteServiceIpml implements CreationActualiteService {

    private final ActualiteRepository actualiteRepository;
    private final HabilitationRepository habilitationRepository;
    private final ActualiteMapper actualiteMapper;
    private final HabilitationMapper habilitationMapper;

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


        return actualite.getId();
    }

}