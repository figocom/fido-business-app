package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.CategoryDAO;
import com.figo.fidobusiness.dao.MaterialDAO;
import com.figo.fidobusiness.dao.MeasurementDAO;
import com.figo.fidobusiness.dao.ProductDAO;
import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.domain.Measurement;
import com.figo.fidobusiness.domain.Product;
import com.figo.fidobusiness.dto.MaterialDTO;
import com.figo.fidobusiness.dto.MaterialIdDTO;
import com.figo.fidobusiness.dto.ProductDTO;
import com.figo.fidobusiness.dto.ProductIdDTO;
import com.figo.fidobusiness.mapper.MaterialMapper;
import com.figo.fidobusiness.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialDAO materialDAO;
    private final CategoryDAO categoryDAO;
    private final MeasurementDAO measurementDAO;
    private final MaterialMapper materialMapper;
    @Override
    public List<MaterialIdDTO> getAll() {
        List<Material> materials=materialDAO.getAll();
        return materialMapper.toDTOList(materials);
    }

    @Override
    public void add(MaterialDTO materialDTO) {
        System.out.println(materialDTO);
        Optional<Category> byId = categoryDAO.getById(materialDTO.getCategoryId());
        Optional<Measurement> byId1 = measurementDAO.getById(materialDTO.getMeasurementId());
        Measurement measurement = byId1.get();
        System.out.println(measurement);
        Category category = byId.get();
        System.out.println(category);
        materialDAO.save(materialMapper.toEntity(materialDTO , category, measurement));
    }

    @Override
    public void update(MaterialIdDTO materialDTO) {
        Material entity = materialMapper.toEntity(materialDTO);
        materialDAO.update(entity);
    }

    @Override
    public void delete( String id) {
        materialDAO.delete(Integer.parseInt(id));
    }

    @Override
    public MaterialIdDTO get(String id) {
        Optional<Material> material=materialDAO.getById(id);
        return material.map(materialMapper::toDTO).orElse(null);

    }

    @Override
    public Material getEntity(String materialId) {
        Optional<Material> byId = materialDAO.getById(materialId);
        return byId.orElseThrow();
    }
}
