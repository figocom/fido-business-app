package com.figo.fidobusiness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialDTO {
    @NotBlank(message = "Code is mandatory")
    private String code;
    private String norma;
    @NotBlank(message = "NameUz is mandatory")
    private String nameUz;
    @NotBlank(message = "NameRu is mandatory")
    private String nameRu;
    private String categoryId;
    @NotBlank(message = "MeasurementId is mandatory")
    private String measurementId;
}
