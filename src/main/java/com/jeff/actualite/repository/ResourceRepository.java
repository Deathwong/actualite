package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Ressource, Long> {
}
