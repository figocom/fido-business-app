package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.CategoryDAO;
import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.dto.CategoryDTO;
import com.figo.fidobusiness.dto.CategoryIdDTO;
import com.figo.fidobusiness.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryIdDTO> getAll() {
       List<Category>categories=categoryDAO.getAllCategories();
       return categoryMapper.toDTOList(categories);
    }

    @Override
    public void add(CategoryDTO categoryDTO) {
        categoryDAO.save(categoryMapper.toEntity(categoryDTO));
    }

    @Override
    public void update(CategoryIdDTO categoryDTO) {
        Category entity = categoryMapper.toEntity(categoryDTO);
        categoryDAO.update(entity);
    }

    @Override
    public void delete( String id) {
        categoryDAO.delete(Integer.parseInt(id));
    }

    @Override
    public CategoryIdDTO get(String id) {
        Optional<Category> category=categoryDAO.getById(id);
        return category.map(categoryMapper::toDTO).orElse(null);

    }
}
