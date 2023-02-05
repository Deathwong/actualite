package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.HabilitationDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Habilitation;
import com.jeff.actualite.domain.mapper.HabilitationMapper;
import com.jeff.actualite.repository.HabilitationRepository;
import com.jeff.actualite.service.HabilitationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class HabilitationServiceIpml implements HabilitationService {

    private final HabilitationMapper habilitationMapper;
    private final HabilitationRepository habilitationRepository;

    @Override
    public void saveAll(List<HabilitationDto> habilitationDtos, Actualite actualiteBdd) {
        if (!CollectionUtils.isEmpty(habilitationDtos)) {
            List<Habilitation> habilitations = habilitationDtos
                    .stream()
                    .map(habilitationDto -> habilitationMapper.map(habilitationDto, actualiteBdd))
                    .toList();
            habilitationRepository.saveAll(habilitations);
        }
    }
}
