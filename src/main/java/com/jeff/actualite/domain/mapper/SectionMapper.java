package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {

    public Section map(SectionDto sectionDto, Actualite actualite) {

        return Section.builder()
                .actualite(actualite)
                .titre(sectionDto.getTitre())
                .texte(sectionDto.getTexte())
                .ordre(sectionDto.getOrdre())
                .build();
    }
}
