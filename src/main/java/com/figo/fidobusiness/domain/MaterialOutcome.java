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
public class MaterialOutcome {
    private Integer id;
    private Material material;
    private String measurement;
    private Integer count;
    private LocalDateTime outcomeDate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;
}
