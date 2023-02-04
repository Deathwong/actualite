package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.FiltreDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Filtre;
import org.springframework.stereotype.Component;

@Component
public class FiltreMapper {

    public Filtre map(FiltreDto filtre, Actualite actualite) {

        return Filtre.builder()
                .actualite(actualite)
                .code(filtre.getCode())
                .valeur(filtre.getValeur())
                .build();
    }
}
