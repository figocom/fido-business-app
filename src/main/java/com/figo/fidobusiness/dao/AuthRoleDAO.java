package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.AuthRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
public class AuthRoleDAO {
    private final JdbcTemplate jdbcTemplate;

    public AuthRoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<AuthRole> findAllByUserId(@NonNull Integer userID) {
        var sql = "select * from auth_role where id in (select role_id from auth_user_role where user_id = ?);";
        List<AuthRole> query = jdbcTemplate.query(sql, new Object[]{userID}, new BeanPropertyRowMapper<>(AuthRole.class));
        System.out.println(query);
        return query;
    }

    public List<AuthRole> findAll() {
        var sql = "select * from auth_role;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AuthRole.class));
    }

    public Collection<AuthRole> findByName(List<String> role) {
        var sql = "select * from auth_role where name in (?);";

        return jdbcTemplate.query(sql, new Object[]{role}, new BeanPropertyRowMapper<>(AuthRole.class));
    }

    public Collection<AuthRole> findById(String role) {
        var sql = "select * from auth_role where id=?;";

        return jdbcTemplate.query(sql, new Object[]{Integer.valueOf(role)}, new BeanPropertyRowMapper<>(AuthRole.class));
    }
}
