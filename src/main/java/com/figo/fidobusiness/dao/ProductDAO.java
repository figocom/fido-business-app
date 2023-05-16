package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> getAll() {
        String sql = "SELECT * FROM product  where deleted=false";
        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
         products.forEach(product -> {
             Category category = namedParameterJdbcTemplate.queryForObject(
                     "select * from category inner join product_category pc on category.id = pc.category_id where product_id=:productId", new MapSqlParameterSource().addValue("productId", product.getId()),
                     new BeanPropertyRowMapper<>(Category.class));

             product.setCategory(category);
         });
         return products;

    }

    public void save(Product entity) {
        var sql = "insert into product(name_uz,name_ru) values (:nameUz ,:nameRu);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("nameUz", entity.getNameUz())
                .addValue("nameRu", entity.getNameRu());
        namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, new String[]{"id"});
        Integer id = (Integer) keyHolder.getKeys().get("id");
        String sql2 = "INSERT INTO product_category(product_id, category_id) VALUES (?, ?)";
        jdbcTemplate.update(sql2, id, entity.getCategory().getId());
    }

    public void update(Product entity) {
        String sql = "UPDATE product SET name_uz=?, name_ru=? WHERE id=?";
        jdbcTemplate.update(sql, entity.getNameUz(), entity.getNameRu(), entity.getId());
    }

    public void delete(int id) {
        String sql = "UPDATE product SET deleted=true WHERE id=?";
        String sql2 = "Delete from product_category where product_id=?";
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(sql2, id);
    }

    public Optional<Product> getById(String id) {
        String sql = "SELECT * FROM product where deleted=false and id=?";

        List<Product> query = jdbcTemplate.query(sql,
                new Integer[]{Integer.valueOf(id)},
                new BeanPropertyRowMapper<>(Product.class));
        return query.stream().findFirst();
    }
}
