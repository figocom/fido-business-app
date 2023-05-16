package com.figo.fidobusiness.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Material {
    private Integer id;
    private String code;
    private String norma;
    private String nameUz;
    private String nameRu;
    private Category category;
    private Measurement measurement;
    private Image image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;
}
