package com.figo.fidobusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialIdDTO {
    private String id;
    private String code;
    private String norma;
    private String nameUz;
    private String nameRu;
    private String categoryName;
    private String measurementName;
}
