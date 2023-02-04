package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Filtre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiltreRepository extends JpaRepository<Filtre, Long> {
}
