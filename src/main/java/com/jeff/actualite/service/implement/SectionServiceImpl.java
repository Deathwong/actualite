package com.jeff.actualite.service.implement;

import com.jeff.actualite.domain.dto.SectionDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.entity.Section;
import com.jeff.actualite.domain.mapper.SectionMapper;
import com.jeff.actualite.repository.SectionRepository;
import com.jeff.actualite.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    @Override
    public Section save(SectionDto sectionDto, Actualite actualite) {
        Section section = sectionMapper.map(sectionDto, actualite);
        return sectionRepository.save(section);
    }

    @Override
    public List<SectionDto> findAllByIdActualite(Long idActualite) {
        List<Section> sections = sectionRepository.findAllByIdActualite(idActualite);

        return sections
                .stream()
                .map(sectionMapper::map)
                .toList();
    }
}
