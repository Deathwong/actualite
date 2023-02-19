package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.service.RechercheActualiteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class RechercheActualiteServiceImpl implements RechercheActualiteService {
    private final ActualiteRepository actualiteRepository;
    private final ActualiteMapper actualiteMapper;

    public Page<ActualiteDto> rechercherWithPageable(Pageable pageable, String titre, Instant dateCreation) {

        Page<Actualite> actualitePage = actualiteRepository.findAll(pageable);

        List<ActualiteDto> actualiteDtos = actualitePage.stream().
                map(actualiteMapper::map)
                .toList();
        return new PageImpl<>(actualiteDtos);
    }

    public List<ActualiteDto> rechercher(String titre) {


        List<Actualite> actualites = actualiteRepository.findAll(ActualiteRepository.hasTitre(titre));

        return actualites.stream()
                .map(actualiteMapper::map)
                .toList();
    }

    public List<ActualiteDto> rechercherWithDateCreation(String dateCreation) {

        Instant date = Instant.parse(dateCreation);

        List<Actualite> actualites = actualiteRepository.findAll(ActualiteRepository.hasDateCreation(date));

        return actualites.stream()
                .map(actualiteMapper::map)
                .toList();
    }
}
