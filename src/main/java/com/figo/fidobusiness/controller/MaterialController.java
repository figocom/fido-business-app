package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dto.*;
import com.figo.fidobusiness.service.CategoryService;
import com.figo.fidobusiness.service.MaterialService;
import com.figo.fidobusiness.service.MeasurementService;
import com.figo.fidobusiness.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/materials")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','CORDINATOR')")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;
    private final CategoryService categoryService;
    private final MeasurementService measurementService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("user/materials");
        List<MaterialIdDTO> materials = materialService.getAll();
        List<CategoryIdDTO> categories = categoryService.getAll();
        List<MeasurementIdDTO> measurement = measurementService.getAll();
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("measurements", measurement);
        modelAndView.addObject("materialsDTO", new MaterialDTO());
        modelAndView.addObject("materialsIdDto", new MaterialIdDTO());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public MaterialIdDTO get(@PathVariable String id){
        return materialService.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView addCategory(@Valid @ModelAttribute MaterialDTO materialsDTO ) {
        materialService.add(materialsDTO);
        return new ModelAndView("redirect:/materials");
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView updateMaterial(@Valid @ModelAttribute MaterialIdDTO materialsDTO) {
        materialService.update(materialsDTO);
        return new ModelAndView("redirect:/materials");
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView deleteMaterial( @PathVariable String id) {
        materialService.delete(id);
        return new ModelAndView("redirect:/materials");
    }


}
