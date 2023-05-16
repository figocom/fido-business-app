package com.figo.fidobusiness.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MaterialIncome {
    private Integer id;
    private Material material;
    private Supplier supplier;
    private Integer count;
    private Double price;
    private LocalDateTime incomeDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;
}
