package com.jeff.actualite.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ActualiteDto {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String titre;

    @Size(max = 400)
    private String Introduction;

    @NotNull
    @PastOrPresent
    private Instant dateCreation;

    @PastOrPresent
    private Instant dateDebutDiffusion;

    @PastOrPresent
    private Instant dateFinDiffusion;

    private boolean prioritaire;

    private boolean active;

    @NotNull
    @PastOrPresent
    private Instant dateMiseAJour;

    List<HabilitationDto> habilitations;

    @NotEmpty
    List<SectionDto> sections;

    List<FiltreDto> filtres;
}
