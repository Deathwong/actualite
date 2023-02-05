package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Section;
import com.jeff.actualite.service.RessourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SectionMapper {

    private final RessourceService ressourceService;

    public Section map(SectionDto sectionDto, Actualite actualite) {

        return Section.builder()
                .actualite(actualite)
                .titre(sectionDto.getTitre())
                .texte(sectionDto.getTexte())
                .ordre(sectionDto.getOrdre())
                .build();
    }

    public SectionDto map(Section section) {

        return SectionDto.builder()
                .id(section.getId())
                .titre(section.getTitre())
                .texte(section.getTexte())
                .ordre(section.getOrdre())
                .avecImage(section.getImage() != null)
                .libelleImage(section.getImage() != null ? section.getImage().getTitre() : null)
                .ressources(ressourceService.findAllByIdSection(section.getId()))
                .build();
    }
}
