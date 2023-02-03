package com.jeff.actualite.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RessourceDto {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String libelle;

    @NotBlank
    @Size(max = 255)
    @URL
    private String url;

    @NotNull
    private Integer ordre;
}
