package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.FiltreDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Filtre;
import com.jeff.actualite.domain.mapper.FiltreMapper;
import com.jeff.actualite.repository.FiltreRepository;
import com.jeff.actualite.service.FiltreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class FiltreServiceImpl implements FiltreService {

    private final FiltreRepository filtreRepository;
    private final FiltreMapper filtreMapper;

    @Override
    public void saveAll(List<FiltreDto> filtreDtos, Actualite actualite) {
        if (!CollectionUtils.isEmpty(filtreDtos)) {
            List<Filtre> filtres = filtreDtos
                    .stream()
                    .map(filtreDto -> filtreMapper.map(filtreDto, actualite))
                    .toList();
            filtreRepository.saveAll(filtres);
        }
    }
}
