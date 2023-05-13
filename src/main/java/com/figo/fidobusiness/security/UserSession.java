package com.figo.fidobusiness.security;

import com.figo.fidobusiness.domain.AuthUser;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserSession {
    public AuthUser getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        var authUserDetails = authentication.getPrincipal();
        if (authUserDetails instanceof AuthUserDetails a)
            return a.getAuthUser();
        return null;
    }

    public Integer getId() {
        AuthUser user = getUser();
        if (user != null)
            return user.getId();
        return null;
    }

}
