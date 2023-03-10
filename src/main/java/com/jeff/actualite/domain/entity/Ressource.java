package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Ressource")
@Table(name = "ressource", schema = "actualite")
@SequenceGenerator(name = "RessourceIdGenerator", sequenceName = "ACTUALITE.RESSOURCE_RES_ID_SEQ", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
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
