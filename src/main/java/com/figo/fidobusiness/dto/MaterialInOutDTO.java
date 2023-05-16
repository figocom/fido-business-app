package com.figo.fidobusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MaterialInOutDTO {
    private Integer id;
    private String materialName;
    private String  code;
    private Integer count;
    private String measurement;
    private String overallPrice;

}
