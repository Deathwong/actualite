package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.HabilitationDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Habilitation;
import com.jeff.actualite.domain.entity.HabilitationId;
import org.springframework.stereotype.Component;

@Component
public class HabilitationMapper {

    public Habilitation map(HabilitationDto habilitation, Actualite actualite) {

        return Habilitation.builder()
                .id(
                        HabilitationId.builder()
                                .codeAcces(habilitation.getCodeAcces())
                                .actualite(actualite)
                                .build()
                )
                .build();
    }
}
