package com.jeff.actualite.domain.mapper;

import com.jeff.actualite.domain.dto.ImageDto;
import com.jeff.actualite.domain.entity.Image;
import com.jeff.actualite.domain.entity.Section;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public Image map(ImageDto image, Section section) {
        return Image.builder()
                .id(section.getId())
                .section(section)
                .titre(image.getTitre())
                .contentType(image.getContentType())
                .taille(image.getTaille())
                .contenu(image.getContenu())
                .build();
    }
}
