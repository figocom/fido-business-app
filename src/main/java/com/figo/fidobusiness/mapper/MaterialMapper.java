package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.domain.Measurement;
import com.figo.fidobusiness.dto.MaterialDTO;
import com.figo.fidobusiness.dto.MaterialIdDTO;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class MaterialMapper {
    public MaterialIdDTO toDTO(Material entity) {
        return MaterialIdDTO.builder().id(String.valueOf(entity.getId())).nameUz(entity.getNameUz()).nameRu(entity.getNameRu()).categoryName(entity.getCategory().getNameUz())
                .code(entity.getCode()).norma(entity.getNorma()).measurementName(entity.getMeasurement().getNameUz()).build();
    }
    public List<MaterialIdDTO> toDTOList(List<Material> products) {
        return products.stream().map(this::toDTO).toList();
    }
    public Material toEntity(MaterialDTO dto, Category category, Measurement measurement) {
        return Material.builder().nameUz(dto.getNameUz()).nameRu(dto.getNameRu()).category(category).code(dto.getCode()).norma(dto.getNorma()).measurement(measurement).build();
    }

    public Material toEntity(MaterialIdDTO dto) {
        return Material.builder().id(Integer.valueOf(dto.getId())).nameUz(dto.getNameUz()).nameRu(dto.getNameRu()).build();
    }
}
