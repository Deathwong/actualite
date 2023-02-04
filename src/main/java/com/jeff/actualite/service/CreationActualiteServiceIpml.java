package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreationActualiteServiceIpml implements CreationActualiteService {
    private final ActualiteRepository actualiteRepository;
    private final ActualiteMapper actualiteMapper;

    @Override
    public Long creer(ActualiteDto actualiteDto) {

        Actualite actualite = actualiteMapper.map(actualiteDto);

        Long actualiteId = actualiteRepository.save(actualite).getId();
        return actualiteId;
    }

}