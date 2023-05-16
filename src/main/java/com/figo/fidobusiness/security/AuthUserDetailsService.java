package com.figo.fidobusiness.security;

import com.figo.fidobusiness.dao.AuthPermissionDAO;
import com.figo.fidobusiness.dao.AuthRoleDAO;
import com.figo.fidobusiness.dao.AuthUserDAO;
import com.figo.fidobusiness.domain.AuthPermission;
import com.figo.fidobusiness.domain.AuthRole;
import com.figo.fidobusiness.domain.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthUserDAO authUserDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthRoleDAO authRoleDao;
    private final AuthPermissionDAO authPermissionDao;

    public AuthUserDetailsService(AuthUserDAO authUserDao, PasswordEncoder passwordEncoder, AuthRoleDAO authRoleDao, AuthPermissionDAO authPermissionDao) {
        this.authUserDao = authUserDao;
        this.passwordEncoder = passwordEncoder;
        this.authRoleDao = authRoleDao;
        this.authPermissionDao = authPermissionDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
        Integer userID = authUser.getId();
        List<AuthRole> roles = authRoleDao.findAllByUserId(userID);
        for (AuthRole role : roles) {
            List<AuthPermission> permissions = authPermissionDao.findAllByRoleId(role.getId());
            role.setPermissions(permissions);
        }
        authUser.setRoles(roles);
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
