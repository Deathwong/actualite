package com.jeff.actualite.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabilitationId implements Serializable {

    @Column(name = "hab_code_acces")
    private String codeAcces;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Actualite actualite;
}
