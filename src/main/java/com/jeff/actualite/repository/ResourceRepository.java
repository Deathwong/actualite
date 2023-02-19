package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Ressource, Long> {

    @Query(value = "select res from Ressource res where res.section.id = :idSection")
    List<Ressource> findAllByIdSection(@Param("idSection") Long idSection);

    @Modifying
    @Transactional
    @Query(value = "delete from actualite.ressource r where r.sec_id in (select r.sec_id from actualite.ressource r " +
            "join actualite.section s on r.sec_id = s.sec_id " +
            "join actualite.actualite a on a.act_id = s.act_id where a.act_id = :idActualite )", nativeQuery = true)
    void deleteAllByIdActualite(@Param("idActualite") Long id);
}
