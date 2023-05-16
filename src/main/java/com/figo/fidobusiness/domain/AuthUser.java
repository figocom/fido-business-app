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
    private String username; //unique
    private String password;
    private String firstName;
    private String lastName;
    private String phone; //unique
    private Collection<AuthRole> roles;
    @Builder.Default
    private StatusEnum status = StatusEnum.ACTIVE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthUser updatedBy;
    private LocalDateTime deletedAt;
    private AuthUser deletedBy;
    private boolean deleted;

    public AuthUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
