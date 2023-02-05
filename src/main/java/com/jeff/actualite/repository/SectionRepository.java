package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query(value = "select sec from Section sec where sec.actualite.id = :idActualite ")
    List<Section> findAllByIdActualite(@Param("idActualite") Long idActualite);
}
