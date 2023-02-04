package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Section")
@Table(name = "section", schema = "actualite")
@SequenceGenerator(name = "SectionIdGenerator", sequenceName = "ACTUALITE.SECTION_SEC_ID_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SectionIdGenerator")
    @Column(name = "sec_id")
    private Long id;

    @Column(name = "sec_titre")
    private String titre;

    @Column(name = "sec_texte")
    private String texte;

    @Column(name = "sec_ordre")
    private Integer ordre;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Actualite actualite;

    @OneToMany(mappedBy = "section")
    List<Ressource> ressources;
}
