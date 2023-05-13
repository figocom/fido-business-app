package com.figo.fidobusiness.domain;

import com.figo.fidobusiness.enums.StatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthRole  {
    private Integer id;
    private String name;
    private String code;
    private Collection<AuthPermission> authPermissions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;

}
