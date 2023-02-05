package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.mapper.ActualiteMapper;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.service.ConsultationActualiteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultationActualiteServiceImpl implements ConsultationActualiteService {

    private final ActualiteRepository actualiteRepository;
    private final ActualiteMapper actualiteMapper;

    @Override
    public ActualiteDto consulter(Long id) {
        Actualite actualite = actualiteRepository.findActualiteById(id);

        return actualiteMapper.map(actualite);
    }
}
