package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dto.MeasurementDTO;
import com.figo.fidobusiness.dto.MeasurementIdDTO;

import java.util.List;

public interface BaseService <DI, D>{
    List<DI> getAll();

    DI get(String id);

    void add(D dto);

    void update(DI updateDto);

    void delete(String id);
}
