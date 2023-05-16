package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.AuthPermission;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import java.util.List;

@Configuration
public class AuthPermissionDAO {

    private final JdbcTemplate jdbcTemplate;

    public AuthPermissionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<AuthPermission> findAllByRoleId(@NonNull Integer roleID) {
        String sql = "select ap.* from auth_role_permission arar inner join auth_permission ap on ap.id = arar.permission_id where arar.role_id =?";
        try {
            List<AuthPermission> query = jdbcTemplate.query(sql, new Object[]{roleID}, new BeanPropertyRowMapper<>(AuthPermission.class));
            System.out.println(query);
            return query;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
