package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;

public interface ActualiteService {
    Actualite save(ActualiteDto actualiteDto, Long id);
}
