package com.jeff.actualite.service.implement;

import com.jeff.actualite.repository.HabilitationRepository;
import com.jeff.actualite.service.HabilitationActualiteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HabilitationActualiteServiceImpl implements HabilitationActualiteService {

    private final HabilitationRepository habilitationRepository;

    @Override
    public boolean verifier(Long id, List<String> codesAcces) {

        boolean existHabilitationByActualiteId = habilitationRepository.existHabilitationByActualiteId(id);
        if (existHabilitationByActualiteId) {
            return habilitationRepository.existCodesAccesByActualiteId(codesAcces, id);
        } else {
            return true;
        }
    }
}
