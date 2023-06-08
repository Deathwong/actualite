package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface ActualiteService {

    public ActualiteDto consulter(Long id);

    public Long creer(ActualiteDto actualiteDto);

    public List<ActualiteDto> rechercherWithDateCreation(String dateCreation);

    public Page<ActualiteDto> rechercherWithPageable(Pageable pageable, String titre, Instant dateCreation);

    public List<ActualiteDto> rechercher(String titre);

    public void modifier(ActualiteDto actualiteDto, Long id);

    public void supprimer(Long id);

    boolean existeActualite(Long actualiteId);
}
