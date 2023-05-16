package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.CategoryDAO;
import com.figo.fidobusiness.dao.MeasurementDAO;
import com.figo.fidobusiness.dao.ProductDAO;
import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Measurement;
import com.figo.fidobusiness.domain.Product;
import com.figo.fidobusiness.dto.MeasurementDTO;
import com.figo.fidobusiness.dto.MeasurementIdDTO;
import com.figo.fidobusiness.dto.ProductDTO;
import com.figo.fidobusiness.dto.ProductIdDTO;
import com.figo.fidobusiness.mapper.MeasurementMapper;
import com.figo.fidobusiness.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private final ProductMapper productMapper;
    @Override
    public List<ProductIdDTO> getAll() {
        List<Product> products=productDAO.getAll();
        return productMapper.toDTOList(products);
    }

    @Override
    public void add(ProductDTO categoryDTO) {
        Optional<Category> byId = categoryDAO.getById(categoryDTO.getCategoryId());
        productDAO.save(productMapper.toEntity(categoryDTO , byId.get()));
    }

    @Override
    public void update(ProductIdDTO categoryDTO) {
        Product entity = productMapper.toEntity(categoryDTO);
        productDAO.update(entity);
    }

    @Override
    public void delete( String id) {
        productDAO.delete(Integer.parseInt(id));
    }

    @Override
    public ProductIdDTO get(String id) {
        Optional<Product> category=productDAO.getById(id);
        return category.map(productMapper::toDTO).orElse(null);

    }
}
