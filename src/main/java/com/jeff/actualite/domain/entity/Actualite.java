package com.jeff.actualite.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity(name = "Actualite")
@Table(name = "actualite", schema = "actualite")
@SequenceGenerator(name = "ActualiteIdGenerator", sequenceName = "ACTUALITE.ACTUALITE_ACT_ID_SEQ", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
    List<Habilitation> habilitations;

    @OneToMany(mappedBy = "actualite")
    @ToString.Exclude
    List<Section> sections;

    @OneToMany(mappedBy = "actualite")
    @ToString.Exclude
    List<Filtre> filtres;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Actualite actualite = (Actualite) o;
        return getId() != null && Objects.equals(getId(), actualite.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
