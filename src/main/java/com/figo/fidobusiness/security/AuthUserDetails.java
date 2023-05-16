package com.figo.fidobusiness.security;


import com.figo.fidobusiness.domain.AuthPermission;
import com.figo.fidobusiness.domain.AuthRole;
import com.figo.fidobusiness.domain.AuthUser;
import com.figo.fidobusiness.enums.StatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class AuthUserDetails implements UserDetails {
    private  final AuthUser authUser;

    public AuthUserDetails(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authRoles = Objects.requireNonNullElse(authUser.getRoles(), Collections.<AuthRole>emptySet());
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        authRoles.forEach(authRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getCode()));
            Collection<AuthPermission> authPermissions = Objects.requireNonNullElse(authRole.getPermissions(), Collections.<AuthPermission>emptySet());
            authPermissions.forEach(authPermission -> {
                authorities.add(new SimpleGrantedAuthority(authPermission.getCode()));
            });
        });
        System.out.println("authorities: " + authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    public Integer getId() {
        return authUser.getId();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !authUser.getStatus().equals(StatusEnum.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StatusEnum.ACTIVE.equals(authUser.getStatus());
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

}
