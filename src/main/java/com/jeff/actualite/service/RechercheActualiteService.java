package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;

import java.util.List;

public interface RechercheActualiteService {
    List<ActualiteDto> rechercher(String titre);

    List<ActualiteDto> rechercherWithDateCreation(String dateCreation);
}
