package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;

public interface ModificationActualiteService {

    void modifier(ActualiteDto actualiteDto, Long id);
}
