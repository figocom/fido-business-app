package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.domain.MaterialIncome;
import com.figo.fidobusiness.domain.Supplier;
import com.figo.fidobusiness.dto.MaterialIdDTO;
import com.figo.fidobusiness.dto.MaterialIncomeDTO;
import com.figo.fidobusiness.dto.MaterialIncomeViewDTO;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MaterialIncomeMapper {
    public MaterialIncomeViewDTO toDTO(MaterialIncome entity) {
        return MaterialIncomeViewDTO.builder()
                .id(entity.getId())
                .measurement(entity.getMaterial().getMeasurement().getNameUz())
                .count(entity.getCount())
                .price(String.valueOf(entity.getPrice()))
                .overallPrice(String.valueOf(entity.getCount()*entity.getPrice()))
                .supplierName(entity.getSupplier().getName())
                .time(entity.getIncomeDate())
                .build();
    }
    public List<MaterialIncomeViewDTO> toDTOList(List<MaterialIncome> products) {
        return products.stream().map(this::toDTO).toList();
    }

    public MaterialIncome toEntity(MaterialIncomeDTO dto, Material material, Supplier supplier) {
      return   MaterialIncome.builder()
                .incomeDate(Timestamp.valueOf(dto.getIncomeDate()+" 00:00:00").toLocalDateTime())
                .material(material)
                .supplier(supplier)
                .count(Integer.valueOf(dto.getCount()))
                .price(Double.valueOf(dto.getPrice()))
                .build();
    }
}
