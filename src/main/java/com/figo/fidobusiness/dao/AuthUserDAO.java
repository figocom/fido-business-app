package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.AuthUser;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AuthUserDAO {
    public Optional<AuthUser> findByUsernameIgnoreCase(String username) {
        return null;
    }

    public Integer save(AuthUser authUser) {
        return null;
    }
}
