package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.MaterialIncomeOutcomeDAO;
import com.figo.fidobusiness.domain.Material;
import com.figo.fidobusiness.domain.MaterialOutcome;
import com.figo.fidobusiness.dto.MaterialIdDTO;
import com.figo.fidobusiness.dto.MaterialInOutDTO;
import com.figo.fidobusiness.dto.MaterialOutDTO;
import com.figo.fidobusiness.dto.MaterialOutcomeDTO;
import com.figo.fidobusiness.mapper.MaterialMapper;
import com.figo.fidobusiness.mapper.MaterialOutcomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialOutcomeServiceImpl implements MaterialOutcomeService{
    private final MaterialIncomeOutcomeDAO dao;
    private final MaterialService materialService;
    private final MaterialOutcomeMapper mapper;
    private final MaterialMapper materialMapper;
    @Override
    public List<MaterialOutDTO> getAll() {
        List<MaterialOutcome> outcomes=dao.getAllOutcomes();
        return mapper.toDTOList(outcomes);
    }

    @Override
    public MaterialOutDTO get(String id) {
        return null;
    }

    @Override
    public void add(MaterialOutcomeDTO dto) {
        MaterialIdDTO idDTO = materialService.get(dto.getMaterialId());
        Material entity = materialMapper.toEntity(idDTO);
        MaterialOutcome outcome = mapper.toEntity(dto, entity);
        dao.saveOutcome(outcome);
    }

    @Override
    public void update(MaterialOutDTO updateDto) {

    }



    @Override
    public void delete(String id) {
        dao.deleteOutcome(Integer.parseInt(id));
    }
}
