package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.*;
import com.figo.fidobusiness.dto.*;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MaterialOutcomeMapper {
    public MaterialOutDTO toDTO(MaterialOutcome entity) {
        return MaterialOutDTO.builder()
                .id(entity.getId())
                .count(entity.getCount())
                .materialName(entity.getMaterial().getNameUz())
                .measurement(entity.getMaterial().getMeasurement().getNameUz())
                .description(entity.getDescription())
                .outcomeDate(entity.getOutcomeDate())
                .build();
    }
    public List<MaterialOutDTO> toDTOList(List<MaterialOutcome> products) {
        return products.stream().map(this::toDTO).toList();
    }

    public MaterialOutcome toEntity(MaterialOutcomeDTO dto , Material material) {
        return   MaterialOutcome.builder()
                .outcomeDate(LocalDateTime.from(dto.getDate().toInstant()))
                .count(Integer.valueOf(dto.getCount()))
                .description(dto.getDescription())
                .material(material)
                .measurement(material.getMeasurement().getNameUz())
                .build();
    }
}
