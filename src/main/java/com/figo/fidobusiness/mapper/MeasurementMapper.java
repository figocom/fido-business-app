package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Measurement;
import com.figo.fidobusiness.dto.CategoryDTO;
import com.figo.fidobusiness.dto.CategoryIdDTO;
import com.figo.fidobusiness.dto.MeasurementDTO;
import com.figo.fidobusiness.dto.MeasurementIdDTO;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class MeasurementMapper {
    public MeasurementIdDTO toDTO(Measurement entity) {
        return MeasurementIdDTO.builder().id(String.valueOf(entity.getId())).nameUz(entity.getNameUz()).nameRu(entity.getNameRu()).build();
    }
    public List<MeasurementIdDTO> toDTOList(List<Measurement> measurements) {
        return measurements.stream().map(this::toDTO).toList();
    }
    public Measurement toEntity(MeasurementDTO dto) {
        return Measurement.builder().nameUz(dto.getNameUz()).nameRu(dto.getNameRu()).build();
    }

    public Measurement toEntity(MeasurementIdDTO dto) {
        return Measurement.builder().id(Integer.valueOf(dto.getId())).nameUz(dto.getNameUz()).nameRu(dto.getNameRu()).build();
    }
}
