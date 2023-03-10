package com.jeff.actualite.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FiltreDto {

    private Long FiltreId;

    @NotBlank
    @Size(max = 50)
    private String code;

    @NotBlank
    @Size(max = 50)
    private String valeur;
}
