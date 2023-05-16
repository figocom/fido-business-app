package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class MeasurementDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Measurement> getAll() {
        String sql = "SELECT * FROM measurement  where deleted=false";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Measurement measurement = new Measurement();
            measurement.setId(resultSet.getInt("id"));
            measurement.setNameUz(resultSet.getString("name_uz"));
            measurement.setNameRu(resultSet.getString("name_ru"));
            return measurement;
        });
    }

    public void save(Measurement entity) {
        String sql = "INSERT INTO measurement(name_uz, name_ru) VALUES (?, ?)";
        jdbcTemplate.update(sql, entity.getNameUz(), entity.getNameRu());
    }

    public void update(Measurement entity) {
        String sql = "UPDATE measurement SET name_uz=?, name_ru=? WHERE id=?";
        jdbcTemplate.update(sql, entity.getNameUz(), entity.getNameRu(), entity.getId());
    }

    public void delete(int id) {
        String sql = "UPDATE measurement SET deleted=true WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Measurement> getById(String id) {
        String sql = "SELECT * FROM measurement where deleted=false and id=?";
        List<Measurement> query = jdbcTemplate.query(sql,
                new Integer[]{Integer.valueOf(id)},
                new BeanPropertyRowMapper<>(Measurement.class));
        return query.stream().findFirst();
    }
}
