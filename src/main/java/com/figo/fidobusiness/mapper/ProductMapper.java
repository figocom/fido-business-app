package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Product;
import com.figo.fidobusiness.dto.ProductDTO;
import com.figo.fidobusiness.dto.ProductIdDTO;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class ProductMapper {
    public ProductIdDTO toDTO(Product entity) {
        return ProductIdDTO.builder().id(String.valueOf(entity.getId())).nameUz(entity.getNameUz()).nameRu(entity.getNameRu()).categoryName(entity.getCategory().getNameUz()).build();
    }
    public List<ProductIdDTO> toDTOList(List<Product> products) {
        return products.stream().map(this::toDTO).toList();
    }
    public Product toEntity(ProductDTO dto, Category category) {
        return Product.builder().nameUz(dto.getNameUz()).nameRu(dto.getNameRu()).category(category).build();
    }

    public Product toEntity(ProductIdDTO dto) {
        return Product.builder().id(Integer.valueOf(dto.getId())).nameUz(dto.getNameUz()).nameRu(dto.getNameRu()).build();
    }
}
