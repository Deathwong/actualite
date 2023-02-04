package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ActualiteMapper {

    public Actualite map(ActualiteDto actualiteDto) {

        Instant date = Instant.now();
        return Actualite.builder()
                .titre(actualiteDto.getTitre())
                .introduction(actualiteDto.getIntroduction())
                .dateCreation(date)
                .dateDebutDiffusion(actualiteDto.getDateDebutDiffusion())
                .dateFinDiffusion(actualiteDto.getDateFinDiffusion())
                .dateMiseAJour(date)
                .prioritaire(actualiteDto.isPrioritaire())
                .active(actualiteDto.isActive())
                .build();
    }
}
