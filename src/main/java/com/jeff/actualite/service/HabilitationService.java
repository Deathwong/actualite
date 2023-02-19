package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.HabilitationDto;
import com.jeff.actualite.domain.entity.Actualite;

import java.util.List;

public interface HabilitationService {
    void saveAll(List<HabilitationDto> habilitationDtos, Actualite actualiteBdd);
}
