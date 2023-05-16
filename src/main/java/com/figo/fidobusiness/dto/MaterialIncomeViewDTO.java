package com.figo.fidobusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialIncomeViewDTO {
    private Integer id;
    private Integer count;
    private String measurement;
    private String price;
    private String overallPrice;
    private LocalDateTime time;
    private String supplierName;
}
