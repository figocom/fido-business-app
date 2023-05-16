package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CategoryDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category where deleted=false";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setNameUz(resultSet.getString("name_uz"));
            category.setNameRu(resultSet.getString("name_ru"));
            return category;
        });
    }

    public void save(Category entity) {
        String sql = "INSERT INTO category (name_uz, name_ru) VALUES (?, ?)";
        jdbcTemplate.update(sql, entity.getNameUz(), entity.getNameRu());
    }

    public void update(Category entity) {
        String sql = "UPDATE category SET name_uz=?, name_ru=? WHERE id=?";
        jdbcTemplate.update(sql, entity.getNameUz(), entity.getNameRu(), entity.getId());
    }

    public void delete(int id) {
        String sql = "UPDATE category SET deleted=true WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Category> getById(String id) {
        String sql = "SELECT * FROM category where deleted=false and id=?";
        List<Category> query = jdbcTemplate.query(sql,
                new Integer[]{Integer.valueOf(id)},
                new BeanPropertyRowMapper<>(Category.class));
        return query.stream().findFirst();
    }
}
