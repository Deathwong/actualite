package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActualiteRepository extends JpaRepository<Actualite, Long> {

    @Query(value = "select act from  Actualite act  where act.id = :id and act.active = true ")
    Actualite findActualiteById(@Param("id") Long id);
}
