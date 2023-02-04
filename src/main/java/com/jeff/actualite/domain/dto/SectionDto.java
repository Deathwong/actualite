package com.jeff.actualite.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDto {

    private Long id;

    @Size(max = 100)
    private String titre;

    @NotBlank
    private String texte;

    @NotBlank
    private Integer ordre;

    List<RessourceDto> ressources;
}