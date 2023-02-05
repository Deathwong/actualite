package com.jeff.actualite.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private Instant date;

    List<@Valid HabilitationDto> habilitations;

    @NotEmpty
    List<@Valid SectionDto> sections;

    List<@Valid FiltreDto> filtres;
}
