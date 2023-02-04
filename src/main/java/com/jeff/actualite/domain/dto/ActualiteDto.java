package com.jeff.actualite.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
    private String introduction;

    @PastOrPresent
    private Instant dateCreation;

    @PastOrPresent
    private Instant dateDebutDiffusion;

    @FutureOrPresent
    private Instant dateFinDiffusion;

    private boolean prioritaire;

    private boolean active;

    @PastOrPresent
    private Instant dateMiseAJour;

    List<@Valid HabilitationDto> habilitations;

    List<SectionDto> sections;

    List<FiltreDto> filtres;
}
