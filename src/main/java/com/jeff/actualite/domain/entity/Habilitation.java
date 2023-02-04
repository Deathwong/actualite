package com.jeff.actualite.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Habilitation")
@Table(name = "habilitation", schema = "actualite")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Habilitation implements Serializable {

    @EmbeddedId
    private HabilitationId id;
}
