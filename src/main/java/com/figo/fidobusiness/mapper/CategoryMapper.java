package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.dto.CategoryDTO;
import com.figo.fidobusiness.dto.CategoryIdDTO;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class CategoryMapper {
    public CategoryIdDTO toDTO(Category category) {
        return CategoryIdDTO.builder().id(String.valueOf(category.getId())).nameUz(category.getNameUz()).nameRu(category.getNameRu()).build();
    }
    public List<CategoryIdDTO> toDTOList(List<Category> categories) {
        return categories.stream().map(this::toDTO).toList();
    }
    public Category toEntity(CategoryDTO categoryDTO) {
        return Category.builder().nameUz(categoryDTO.getNameUz()).nameRu(categoryDTO.getNameRu()).build();
    }

    public Category toEntity(CategoryIdDTO categoryDTO) {
        return Category.builder().id(Integer.valueOf(categoryDTO.getId())).nameUz(categoryDTO.getNameUz()).nameRu(categoryDTO.getNameRu()).build();
    }
}
