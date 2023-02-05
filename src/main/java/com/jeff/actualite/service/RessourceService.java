package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.RessourceDto;

import java.util.List;

public interface RessourceService {
    List<RessourceDto> findAllByIdSection(Long idSection);
}
