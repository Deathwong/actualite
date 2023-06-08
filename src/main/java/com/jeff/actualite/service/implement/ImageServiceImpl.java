package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.entity.Image;
import com.jeff.actualite.repository.ImageRepository;
import com.jeff.actualite.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public boolean existeImage(Long imageId) {

        Optional<Image> image = imageRepository.findById(imageId);

        return image.isPresent();
    }
}
