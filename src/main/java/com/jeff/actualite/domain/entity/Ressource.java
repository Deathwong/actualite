package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Ressource")
@Table(name = "ressource")
@SequenceGenerator(name = "RessourceIdGenerator", sequenceName = "RESSOURCE_RES_ID_SEQ")
@Data
@NoArgsConstructor
public class Ressource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RessourceIdGenerator")
    @Column(name = "res_id")
    private Long id;

    @Column(name = "res_libelle")
    private String libelle;

    @Column(name = "res_url")
    private String url;

    @Column(name = "res_ordre")
    private Integer ordre;

    @ManyToOne
    @JoinColumn(name = "sec_id")
    private Section section;
}
