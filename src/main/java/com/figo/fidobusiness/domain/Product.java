package com.figo.fidobusiness.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class Product {
    private Integer id;
    private String nameUz;
    private String nameRu;
    private Category category;
    private Image image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;
}
