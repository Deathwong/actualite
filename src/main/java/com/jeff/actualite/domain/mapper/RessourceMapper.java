package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.RessourceDto;
import com.jeff.actualite.domain.entity.Ressource;
import com.jeff.actualite.domain.entity.Section;
import org.springframework.stereotype.Component;

@Component
public class RessourceMapper {

    public Ressource map(RessourceDto ressourceDto, Section section) {

        return Ressource.builder()
                .section(section)
                .url(ressourceDto.getUrl())
                .libelle(ressourceDto.getLibelle())
                .ordre(ressourceDto.getOrdre())
                .build();
    }

    public RessourceDto map(Ressource ressource) {
        return RessourceDto.builder()
                .libelle(ressource.getLibelle())
                .url(ressource.getUrl())
                .ordre(ressource.getOrdre())
                .build();
    }
}
