package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface ActualiteService {

    ActualiteDto consulter(Long id);

    Long creer(ActualiteDto actualiteDto);

    List<ActualiteDto> rechercherWithDateCreation(String dateCreation);

    Page<ActualiteDto> rechercherWithPageable(Pageable pageable, String titre, Instant dateCreation);

    List<ActualiteDto> rechercher(String titre);

    void modifier(ActualiteDto actualiteDto, Long id);

    void supprimer(Long id);

    boolean existActualite(Long actualiteId);
}
