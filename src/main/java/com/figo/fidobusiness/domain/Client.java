package com.figo.fidobusiness.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Client {
    private Integer id;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;
}
