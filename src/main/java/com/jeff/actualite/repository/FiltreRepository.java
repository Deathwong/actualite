package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Filtre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FiltreRepository extends JpaRepository<Filtre, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from actualite.filtre flt where flt.act_id = :idActualite", nativeQuery = true)
    void deleteAllByIdActualite(@Param("idActualite") Long id);
}