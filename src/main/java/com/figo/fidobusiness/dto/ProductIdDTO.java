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
public class ProductIdDTO {
    private String id;
    private String nameUz;
    private String nameRu;
    private String categoryName;
}
