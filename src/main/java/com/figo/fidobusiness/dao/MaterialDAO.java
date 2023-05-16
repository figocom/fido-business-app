package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.domain.Measurement;
import com.figo.fidobusiness.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class MaterialDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Material> getAll() {
        String sql = "SELECT * FROM material where deleted=false";
        List<Material> materials = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Material.class));
        materials.forEach(material -> {
            Category category = namedParameterJdbcTemplate.queryForObject(
                    "select * from category inner join material_category mc on category.id = mc.category_id where material_id=:materialId", new MapSqlParameterSource().addValue("materialId", material.getId()),
                    new BeanPropertyRowMapper<>(Category.class));
            material.setCategory(category);
            Measurement measurement= namedParameterJdbcTemplate.queryForObject(
                    "select * from measurement inner join material_measurement mm on measurement.id = mm.measurement_id where material_id=:materialId", new MapSqlParameterSource().addValue("materialId", material.getId()),
                    new BeanPropertyRowMapper<>(Measurement.class));
            material.setMeasurement(measurement);
        });
        return materials;
    }

    public void save(Material entity) {
        var sql = "insert into material(name_uz, name_ru, code, norma) values (:nameUz ,:nameRu,:code,:norma);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("nameUz", entity.getNameUz())
                .addValue("nameRu", entity.getNameRu())
                .addValue("code", entity.getCode())
                .addValue("norma", entity.getNorma());
        namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, new String[]{"id"});
        Integer id = (Integer) keyHolder.getKeys().get("id");
        String sql2 = "INSERT INTO material_category(material_id, category_id) VALUES (?, ?)";
        jdbcTemplate.update(sql2, id, entity.getCategory().getId());
        String sql3 = "INSERT INTO material_measurement(material_id, measurement_id) VALUES (?, ?)";
        jdbcTemplate.update(sql3, id, entity.getMeasurement().getId());
    }

    public void update(Material entity) {
        String sql = "UPDATE material SET name_uz=?, name_ru=? WHERE id=?";
        jdbcTemplate.update(sql, entity.getNameUz(), entity.getNameRu(), entity.getId());
    }

    public void delete(int id) {
        String sql = "UPDATE material SET deleted=true WHERE id=?";
        String sql2="DELETE FROM material_category WHERE material_id=?";
        String sql3="DELETE FROM material_measurement WHERE material_id=?";
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(sql2, id);
        jdbcTemplate.update(sql3, id);
    }

    public Optional<Material> getById(String id) {
        String sql = "SELECT * FROM material where deleted=false and id=?";

        List<Material> query = jdbcTemplate.query(sql,
                new Integer[]{Integer.valueOf(id)},
                new BeanPropertyRowMapper<>(Material.class));
        return query.stream().findFirst();
    }
}
