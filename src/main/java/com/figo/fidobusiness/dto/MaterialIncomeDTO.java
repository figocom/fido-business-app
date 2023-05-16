package com.figo.fidobusiness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialIncomeDTO {
    @NotBlank(message = "material can not be null")
    private String material_id;
    @NotBlank(message = "supplier can not be null")
    private String supplier_id;
    @NotBlank(message = "count can not be null")
    private String count;
    @NotBlank(message = "price can not be null")
    private String price;
    private String incomeDate;
}
