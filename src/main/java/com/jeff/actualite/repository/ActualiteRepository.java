package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Actualite;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface ActualiteRepository extends JpaRepository<Actualite, Long>, JpaSpecificationExecutor<Actualite> {

    Actualite findActualiteByIdAndActiveTrue(@Param("id") Long id);

    static Specification<Actualite> hasTitre(String titre) {
        return (Actualite, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(Actualite.get("titre"), titre);
    }

    static Specification<Actualite> hasDateCreation(Instant dateCreation) {
        return (Actualite, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(Actualite.get("dateCreation")
                , dateCreation);
    }
}
