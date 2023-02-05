package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.FiltreDto;
import com.jeff.actualite.domain.entity.Actualite;

import java.util.List;

public interface FiltreService {

    void saveAll(List<FiltreDto> filtreDtos, Actualite actualite);
}
