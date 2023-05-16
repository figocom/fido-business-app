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
public class MaterialOutcomeDTO {
    @NotBlank(message = "material can not be null")
    private String materialId;
    @NotBlank(message = "count can not be null")
    private String count;
    private Date date;
    private String description;
}
