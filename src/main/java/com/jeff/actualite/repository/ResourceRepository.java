package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Ressource, Long> {

    @Query(value = "select res from Ressource res where res.section.id = :idSection")
    List<Ressource> findAllByIdSection(@Param("idSection") Long idSection);
}
