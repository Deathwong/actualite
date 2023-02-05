package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.RessourceDto;
import com.jeff.actualite.domain.entity.Ressource;
import com.jeff.actualite.domain.mapper.RessourceMapper;
import com.jeff.actualite.repository.ResourceRepository;
import com.jeff.actualite.service.RessourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RessourceServiceImpl implements RessourceService {

    private final ResourceRepository resourceRepository;
    private final RessourceMapper ressourceMapper;

    @Override
    public List<RessourceDto> findAllByIdSection(Long idSection) {

        List<Ressource> ressources = resourceRepository.findAllByIdSection(idSection);
        return ressources
                .stream()
                .map(ressourceMapper::map)
                .toList();
    }
}
