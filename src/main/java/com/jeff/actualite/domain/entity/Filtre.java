package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Filtre")
@Table(name = "filtre", schema = "actualite")
@SequenceGenerator(name = "FiltreIdGenerator", sequenceName = "ACTUALITE.FILTRE_FLT_ID_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
public class Filtre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FiltreIdGenerator")
    @Column(name = "flt_id")
    private Long FiltreId;

    @Column(name = "flt_code")
    private String code;

    @Column(name = "flt_valeur")
    private String valeur;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Actualite actualite;
}
