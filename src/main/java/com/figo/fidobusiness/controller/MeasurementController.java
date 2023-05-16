package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dto.CategoryDTO;
import com.figo.fidobusiness.dto.CategoryIdDTO;
import com.figo.fidobusiness.dto.MeasurementDTO;
import com.figo.fidobusiness.dto.MeasurementIdDTO;
import com.figo.fidobusiness.service.CategoryService;
import com.figo.fidobusiness.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/measurement")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','CORDINATOR')")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("user/measurement");
        List<MeasurementIdDTO> measurements = measurementService.getAll();
        modelAndView.addObject("measurements", measurements);
        modelAndView.addObject("measurementDTO", new CategoryDTO());
        modelAndView.addObject("measurementIdDto", new CategoryIdDTO());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public MeasurementIdDTO get(@PathVariable String id){
        return measurementService.get(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView addCategory(@Valid @ModelAttribute MeasurementDTO measurementDTO ) {
        measurementService.add(measurementDTO);
        return new ModelAndView("redirect:/measurement");
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView updateMeasurement(@Valid @ModelAttribute MeasurementIdDTO measurementDTO) {
        measurementService.update(measurementDTO);
        return new ModelAndView("redirect:/measurement");
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView deleteMeasurement( @PathVariable String id) {
        measurementService.delete(id);
        return new ModelAndView("redirect:/measurement");
    }


}
