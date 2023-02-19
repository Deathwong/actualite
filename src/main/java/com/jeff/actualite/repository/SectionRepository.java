package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query(value = "select sec from Section sec where sec.actualite.id = :idActualite ")
    List<Section> findAllByIdActualite(@Param("idActualite") Long idActualite);

    @Modifying
    @Transactional
    @Query(value = "delete from actualite.section s where s.sec_id in (select distinct s.sec_id " +
            "from actualite.section s join actualite.actualite a on s.act_id = a.act_id where a.act_id = :idActualite)",
            nativeQuery = true)
    void deleteAllByIdActualite(@Param("idActualite") Long id);
}
