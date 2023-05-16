package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.MeasurementDAO;
import com.figo.fidobusiness.domain.Category;
import com.figo.fidobusiness.domain.Measurement;
import com.figo.fidobusiness.dto.CategoryDTO;
import com.figo.fidobusiness.dto.CategoryIdDTO;
import com.figo.fidobusiness.dto.MeasurementDTO;
import com.figo.fidobusiness.dto.MeasurementIdDTO;
import com.figo.fidobusiness.mapper.MeasurementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService{
    private final MeasurementDAO measurementDAO;
    private final MeasurementMapper measurementMapper;
    @Override
    public List<MeasurementIdDTO> getAll() {
        List<Measurement> measurements=measurementDAO.getAll();
        return measurementMapper.toDTOList(measurements);
    }

    @Override
    public void add(MeasurementDTO categoryDTO) {
        measurementDAO.save(measurementMapper.toEntity(categoryDTO));
    }

    @Override
    public void update(MeasurementIdDTO categoryDTO) {
        Measurement entity = measurementMapper.toEntity(categoryDTO);
        measurementDAO.update(entity);
    }

    @Override
    public void delete( String id) {
        measurementDAO.delete(Integer.parseInt(id));
    }

    @Override
    public MeasurementIdDTO get(String id) {
        Optional<Measurement> category=measurementDAO.getById(id);
        return category.map(measurementMapper::toDTO).orElse(null);

    }
}
