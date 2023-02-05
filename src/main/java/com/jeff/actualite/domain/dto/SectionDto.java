package com.jeff.actualite.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionDto {

    private Long id;

    @Size(max = 100)
    private String titre;

    @NotBlank
    private String texte;

    @NotNull
    private Integer ordre;

    private boolean avecImage;

    private String libelleImage;

    @Valid
    private ImageDto image;

    List<@Valid RessourceDto> ressources;
}
