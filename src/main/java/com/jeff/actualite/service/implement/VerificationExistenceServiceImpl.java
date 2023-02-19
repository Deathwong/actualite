package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Image;
import com.jeff.actualite.repository.ActualiteRepository;
import com.jeff.actualite.repository.ImageRepository;
import com.jeff.actualite.service.VerificationExistenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VerificationExistenceServiceImpl implements VerificationExistenceService {

    private final ActualiteRepository actualiteRepository;
    private final ImageRepository imageRepository;

    @Override
    public boolean existeActualite(Long actualiteId) {

        Optional<Actualite> actualite = actualiteRepository.findById(actualiteId);

        return actualite.isPresent();
    }

    @Override
    public boolean existeImage(Long imageId) {
        
        Optional<Image> image = imageRepository.findById(imageId);

        return image.isPresent();
    }
}
