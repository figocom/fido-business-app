package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dto.CategoryDTO;
import com.figo.fidobusiness.dto.CategoryIdDTO;
import com.figo.fidobusiness.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/category")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','CORDINATOR')")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ModelAndView catalog() {
        ModelAndView modelAndView = new ModelAndView("user/catalog");
        List<CategoryIdDTO> categories = categoryService.getAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("categoryDTO", new CategoryDTO());
        modelAndView.addObject("categoryIdDto", new CategoryIdDTO());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public CategoryIdDTO get(@PathVariable String id){
        return categoryService.get(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView addCategory(@Valid @ModelAttribute CategoryDTO categoryDTO , BindingResult bindingResult) {
        categoryService.add(categoryDTO);
        return new ModelAndView("redirect:/category/all");
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView updateCategory(@Valid @ModelAttribute CategoryIdDTO categoryDTO, BindingResult bindingResult) {
        categoryService.update(categoryDTO);
        return new ModelAndView("redirect:/category/all");
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView deleteCategory( @PathVariable String id) {
        categoryService.delete(id);
        return new ModelAndView("redirect:/category/all");
    }


}
