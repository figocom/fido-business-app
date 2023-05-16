package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SupplierDAO {
    private final JdbcTemplate jdbcTemplate;
    public List<Supplier> getAllSuppliers() {
        var sql = "SELECT * FROM supplier where deleted=false";
       return jdbcTemplate.query(sql, (rs, rowNum) -> {
            var supplier = new Supplier();
            supplier.setId(rs.getInt("id"));
            supplier.setName(rs.getString("name"));
            supplier.setPhone(rs.getString("phone"));
            return supplier;
        });
    }

    public void save(Supplier build) {
        var sql = "insert into supplier(name, phone) values (? ,?);";
        jdbcTemplate.update(sql, build.getName(), build.getPhone());
    }

    public void delete(int i) {
        var sql = "update supplier set deleted=true where id=?;";
        jdbcTemplate.update(sql, i);
    }

    public Supplier getById(Integer integer) {
        var sql = "SELECT * FROM supplier where deleted=false and id=?";
        return jdbcTemplate.query(sql, new Object[]{integer}, new BeanPropertyRowMapper<>(Supplier.class)).stream().findFirst().orElse(null);
    }
}
