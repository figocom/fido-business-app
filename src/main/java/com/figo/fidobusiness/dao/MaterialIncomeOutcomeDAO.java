package com.figo.fidobusiness.dao;

import com.figo.fidobusiness.domain.MaterialIncome;
import com.figo.fidobusiness.domain.MaterialOutcome;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MaterialIncomeOutcomeDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<MaterialIncome> getAllIncomes() {
        var sql = "SELECT * FROM material_income where deleted=false";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MaterialIncome.class));
    }

    public void save(MaterialIncome entity) {
       var sql = "insert into material_income(material_id, supplier_id, count, price, income_date) values (?,?,?,?,?);";
         jdbcTemplate.update(sql, entity.getMaterial().getId(), entity.getSupplier().getId(), entity.getCount(), entity.getPrice(), entity.getIncomeDate());
    }

    public void delete(String id) {
        var sql = "update material_income set deleted=true where id=?";
        jdbcTemplate.update(sql, id);
    }

    public List<MaterialOutcome> getAllOutcomes() {
        var sql = "SELECT * FROM material_outcome where deleted=false";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MaterialOutcome.class));
    }

    public void deleteOutcome(int i) {
        var sql = "update material_outcome set deleted=true where id=?";
        jdbcTemplate.update(sql, i);
    }

    public void saveOutcome(MaterialOutcome outcome) {
        var sql = "insert into material_outcome(count, outcomedate, description) values (?,?,?);";
        jdbcTemplate.update(sql, outcome.getCount(), outcome.getOutcomeDate(), outcome.getDescription());
         }
}
