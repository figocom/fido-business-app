package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.MaterialIncomeOutcomeDAO;
import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.domain.MaterialIncome;
import com.figo.fidobusiness.domain.Supplier;
import com.figo.fidobusiness.dto.MaterialIncomeDTO;
import com.figo.fidobusiness.dto.MaterialIncomeViewDTO;
import com.figo.fidobusiness.mapper.MaterialIncomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MaterialIncomeServiceImpl implements MaterialIncomeService{
    private final MaterialIncomeOutcomeDAO dao;
    private final MaterialIncomeMapper mapper;
    private final UserService userService;
    private final MaterialService materialService;
    private final MeasurementService measurementService;
    @Override
    public List<MaterialIncomeViewDTO> getAll() {
       List<MaterialIncome> incomes=dao.getAllIncomes();
       return mapper.toDTOList(incomes);
    }

    @Override
    public MaterialIncomeViewDTO get(String id) {
        return null;
    }

    @Override
    public void add(MaterialIncomeDTO dto) {
        Supplier supplier=userService.getSupplierByID(dto.getSupplier_id());
        Material material= materialService.getEntity(dto.getMaterial_id());
        System.out.println(material);
        MaterialIncome entity = mapper.toEntity(dto, material, supplier);
        entity.setMaterial(material);
        dao.save(entity);
    }

    @Override
    public void update(MaterialIncomeViewDTO updateDto) {

    }

    @Override
    public void delete(String id) {
       dao.delete(id);
    }
}
