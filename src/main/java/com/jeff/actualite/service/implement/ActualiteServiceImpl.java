package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.service.ActualiteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActualiteServiceImpl implements ActualiteService {
    private final ActualiteRepository actualiteRepository;
    private final ActualiteMapper actualiteMapper;

    public Actualite save(ActualiteDto actualiteDto, Long id) {
        Actualite actualite = actualiteMapper.map(actualiteDto);
        actualite.setId(id);

        Actualite actualiteOfTheBase = actualiteRepository.findActualiteById(id);

        actualite.setDateCreation(actualiteOfTheBase.getDateCreation());

        return actualiteRepository.save(actualite);
    }
}
