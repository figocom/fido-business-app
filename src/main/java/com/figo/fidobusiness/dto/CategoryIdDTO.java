package com.figo.fidobusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryIdDTO {
    private String id;
    private String nameUz;
    private String nameRu;
}
