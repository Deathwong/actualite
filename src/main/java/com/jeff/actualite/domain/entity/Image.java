package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Image")
@Table(name = "image", schema = "actualite")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {

    @Id
    private Long id;

    @Column(name = "img_libelle")
    private String titre;

    @Column(name = "img_content_type")
    private String contentType;

    @Column(name = "img_taille")
    private Integer taille;

    @Column(name = "img_contenu")
    private Byte[] contenu;

    //    @MapsId("sec_id")
    @OneToOne
    @JoinColumn(name = "sec_id")
    private Section section;
}
