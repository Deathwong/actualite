package com.jeff.actualite.service;

import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Section;

public interface SectionService {
    Section save(SectionDto sectionDto, Actualite actualite);
}
