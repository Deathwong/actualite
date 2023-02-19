package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from actualite.image i where sec_id in (select i.sec_id " +
            "from actualite.image i join actualite.section s on i.sec_id = s.sec_id join actualite.actualite a " +
            "on s.act_id = a.act_id  where a.act_id = :idActualite)", nativeQuery = true)
    void deleteAllByIdActualite(@Param("idActualite") Long idActualite);


    @Query(value = "select i.id from actualite.image i join actualite.section s on i.sec_id = s.sec_id join " +
            "actualite.actualite a on s.act_id = a.act_id  where a.act_id = :idActualite", nativeQuery = true)
    Long getIdImageByIdActualite(@Param("idActualite") Long idActualite);
}
