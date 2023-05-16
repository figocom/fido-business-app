package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ClientDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Client> getAllClients() {
        var sql = "SELECT * FROM client where deleted=false";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            var client = new Client();
            client.setId(rs.getInt("id"));
            client.setName(rs.getString("name"));
            client.setPhone(rs.getString("phone"));
            return client;
        });
    }

    public void save(Client build) {
        var sql = "insert into client(name, phone) values (? ,?);";
        jdbcTemplate.update(sql, build.getName(), build.getPhone());
    }

    public void delete(int i) {
        var sql = "update client set deleted=true where id=?;";
        jdbcTemplate.update(sql, i);
    }
}
