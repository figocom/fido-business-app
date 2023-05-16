package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.domain.MaterialOutcome;
import com.figo.fidobusiness.dto.MaterialIncomeDTO;
import com.figo.fidobusiness.dto.MaterialIncomeViewDTO;
import com.figo.fidobusiness.dto.MaterialOutDTO;
import com.figo.fidobusiness.service.MaterialIncomeService;
import com.figo.fidobusiness.service.MaterialOutcomeService;
import com.figo.fidobusiness.service.MaterialService;
import com.figo.fidobusiness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/warehouse")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','CORDINATOR')")
@RequiredArgsConstructor
public class MaterialsWarehouse {
    private final MaterialIncomeService materialIncomeService;
    private final MaterialOutcomeService materialOutcomeService;
    private final MaterialService materialService;
    private final UserService userService;
    @GetMapping("/materials/income")
    ModelAndView getMaterials() {
        List<MaterialOutDTO> all1 = materialOutcomeService.getAll();
        List<MaterialIncomeViewDTO> all = materialIncomeService.getAll();
        Double total = all.stream().mapToDouble(materialIncomeViewDTO -> Double.parseDouble(materialIncomeViewDTO.getOverallPrice())).sum();
        ModelAndView modelAndView = new ModelAndView("user/materialInOut");
        modelAndView.addObject("materials", all);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    @GetMapping("/addMaterial")
    ModelAndView addMaterial() {
        ModelAndView modelAndView = new ModelAndView("user/MaterialIn");
        modelAndView.addObject("materials", materialService.getAll());
        modelAndView.addObject("suppliers", userService.getAllSuppliers());
        modelAndView.addObject("materialIncome", new MaterialIncomeDTO());
        return modelAndView;
    }
    @PostMapping("/createIncome")
    @PreAuthorize("hasRole('CORDINATOR')")
    String createIncome(MaterialIncomeDTO materialIncomeDTO) {
        materialIncomeService.add(materialIncomeDTO);
        return "redirect:/warehouse/materials/income";
    }

}
