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

public class AuthUser  {
    private Integer id;
    private String username;
    private String password;
    private Collection<AuthRole> authRoles;
    @Builder.Default
    private StatusEnum status = StatusEnum.ACTIVE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;

}
