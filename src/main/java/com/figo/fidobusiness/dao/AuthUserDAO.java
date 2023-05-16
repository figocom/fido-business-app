package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.AuthUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;

@Configuration
public class AuthUserDAO {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthUserDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<AuthUser> findByUsernameIgnoreCase(String username) {
        var sql = "select * from auth_user where username = ? and deleted=false;";
        List<AuthUser> query = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(AuthUser.class));
        return query.stream().findFirst();
    }

    public Integer save(AuthUser entity) {
        var sql = "insert into auth_user (username, password , first_name,last_name,phone) VALUES (:username, :password,:firstName,:lastName,:phone)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("username", entity.getUsername())
                .addValue("password", entity.getPassword())
                .addValue("firstName", entity.getFirstName())
                .addValue("lastName", entity.getLastName())
                .addValue("phone", entity.getPhone());
        namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, new String[]{"id"});
        Integer id = (Integer) keyHolder.getKeys().get("id");
        jdbcTemplate.update("INSERT INTO fido_business_app.public.auth_user_role (user_id, role_id) VALUES (?, ?);",id, entity.getRoles().stream().findFirst().get().getId());
    return id;


    }

    public List<AuthUser> getAllUsers() {
        var sql = "select * from auth_user where deleted=false;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AuthUser.class));
    }

    public void delete(int i) {
        var sql = "update auth_user set deleted=true where id=?;";
        jdbcTemplate.update(sql, i);
    }

    public Optional<AuthUser> getById(String id) {
        var sql = "select * from auth_user where id=? and deleted=false;";
        List<AuthUser> query = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(AuthUser.class));
        return query.stream().findFirst();
    }
}
