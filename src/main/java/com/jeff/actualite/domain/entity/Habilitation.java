package com.jeff.actualite.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Habilitation")
@Table(name = "habilitation", schema = "actualite")
@NoArgsConstructor
@Data
public class Habilitation implements Serializable {

    @EmbeddedId
    private HabilitationId id;
}
