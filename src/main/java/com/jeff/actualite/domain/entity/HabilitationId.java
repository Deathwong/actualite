package com.jeff.actualite.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class HabilitationId implements Serializable {

    @Column(name = "HAB_CODE_ACCES")
    private Long codeAcces;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Actualite actualite;
}
