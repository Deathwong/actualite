package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import org.springframework.stereotype.Component;

@Component
public class ActualiteMapper {

    public Actualite map(ActualiteDto actualiteDto) {

        return Actualite.builder()
                .titre(actualiteDto.getTitre())
                .introduction(actualiteDto.getIntroduction())
                .dateDebutDiffusion(actualiteDto.getDateDebutDiffusion())
                .dateFinDiffusion(actualiteDto.getDateFinDiffusion())
                .prioritaire(actualiteDto.isPrioritaire())
                .active(actualiteDto.isActive())
                .build();
    }
}
