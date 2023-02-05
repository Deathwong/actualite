package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;

public interface ConsultationActualiteService {
    ActualiteDto consulter(Long id);
}
