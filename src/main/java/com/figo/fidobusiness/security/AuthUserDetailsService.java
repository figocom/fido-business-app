package com.figo.fidobusiness.security;

import com.figo.fidobusiness.dao.AuthUserDAO;
import com.figo.fidobusiness.domain.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthUserDAO authUserDao;
    private final PasswordEncoder passwordEncoder;

    public AuthUserDetailsService(AuthUserDAO authUserDao, PasswordEncoder passwordEncoder) {
        this.authUserDao = authUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
        return new AuthUserDetails(authUser);
    }

    public UserDetails checkPassword(String username, String password) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
        if (passwordEncoder.matches(password, authUser.getPassword())) {
            return new AuthUserDetails(authUser);
        }
        throw new UsernameNotFoundException("Bad Credentials");
    }

    public void save(AuthUser authUser) {
        authUserDao.save(authUser);
    }
}
