package com.jeff.actualite.repository;

import com.jeff.actualite.domain.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
