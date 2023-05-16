package com.figo.fidobusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialOutDTO {
    private Integer id;
    private Integer count;
    private LocalDateTime outcomeDate;
    private String description;
    private String totalPrice;
    private String measurement;
    private String materialName;
}
