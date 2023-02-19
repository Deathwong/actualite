package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Habilitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HabilitationRepository extends JpaRepository<Habilitation, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from Habilitation hbt where hbt.id.actualite.id = :idActualite")
    void deleteAllByIdActualite(@Param("idActualite") Long id);

    @Query(value = "select exists (select hab.act_id from actualite.habilitation hab where hab.act_id = :idActualite)",
            nativeQuery = true)
    boolean existHabilitationByActualiteId(@Param("idActualite") Long id);

    @Query(value = "select exists (select hab.id from Habilitation hab where hab.id.codeAcces in (:codesAcces)" +
            " and hab.id.actualite.id = :idActualite)")
    boolean existCodesAccesByActualiteId(@Param("codesAcces") List<String> codesAcces, Long idActualite);
}
