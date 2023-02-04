package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity(name = "Actualite")
@Table(name = "actualite", schema = "actualite")
@SequenceGenerator(name = "ActualiteIdGenerator", sequenceName = "ACTUALITE.ACTUALITE_ACT_ID_SEQ", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Actualite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ActualiteIdGenerator")
    @Column(name = "act_id")
    private Long id;

    @Column(name = "act_titre")
    private String titre;

    @Column(name = "act_introduction")
    private String introduction;

    @Column(name = "act_date_creation")
    @CreationTimestamp
    private Instant dateCreation;

    @Column(name = "act_date_debut_diffusion")
    private Instant dateDebutDiffusion;

    @Column(name = "act_date_fin_diffusion")
    private Instant dateFinDiffusion;

    @Column(name = "act_prioritaire")
    private boolean prioritaire;

    @Column(name = "act_active")
    private boolean active;

    @Column(name = "act_date_modification")
    @UpdateTimestamp
    private Instant dateMiseAJour;

    @OneToMany(mappedBy = "id.actualite")
    List<Habilitation> habilitations;

    @OneToMany(mappedBy = "actualite")
    List<Section> sections;

    @OneToMany(mappedBy = "actualite")
    List<Filtre> filtres;
}
