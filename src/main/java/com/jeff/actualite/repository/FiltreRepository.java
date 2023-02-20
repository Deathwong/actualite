package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Filtre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FiltreRepository extends JpaRepository<Filtre, Long> {

    @Modifying
    @Query(value = "delete from Filtre flt where flt.actualite.id = :idActualite")
    void deleteAllByIdActualite(@Param("idActualite") Long id);
}