package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActualiteMapper {

    private final SectionService sectionService;

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

    public ActualiteDto map(Actualite actualite) {

        return ActualiteDto.builder()
                .titre(actualite.getTitre())
                .introduction(actualite.getIntroduction())
                .date(
                        actualite.getDateDebutDiffusion() != null ? actualite.getDateDebutDiffusion() : actualite
                                .getDateMiseAJour()
                )
                .prioritaire(actualite.isPrioritaire())
                .sections(sectionService.findAllByIdActualite(actualite.getId()))
                .build();
    }
}
