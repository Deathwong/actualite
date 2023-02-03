package com.jeff.actualite.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String titre;

    @NotBlank
    @Size(max = 255)
    private String contentType;

    @NotNull
    private Long taille;

    @NotNull
    private Byte[] contenu;
}
