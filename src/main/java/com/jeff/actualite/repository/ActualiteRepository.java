package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualiteRepository extends JpaRepository<Actualite, Long> {
}
