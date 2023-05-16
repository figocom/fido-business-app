package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dto.CategoryIdDTO;
import com.figo.fidobusiness.dto.ProductDTO;
import com.figo.fidobusiness.dto.ProductIdDTO;
import com.figo.fidobusiness.service.CategoryService;
import com.figo.fidobusiness.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/product")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','CORDINATOR')")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("user/product");
        List<ProductIdDTO> products = productService.getAll();
        List<CategoryIdDTO> categories = categoryService.getAll();
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("productDTO", new ProductDTO());
        modelAndView.addObject("productIdDto", new ProductIdDTO());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ProductIdDTO get(@PathVariable String id){
        return productService.get(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView addCategory(@Valid @ModelAttribute ProductDTO productDTO ) {
        productService.add(productDTO);
        return new ModelAndView("redirect:/product");
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView updateProduct(@Valid @ModelAttribute ProductIdDTO productDTO) {
        productService.update(productDTO);
        return new ModelAndView("redirect:/product");
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('CORDINATOR')")
    public ModelAndView deleteProduct( @PathVariable String id) {
        productService.delete(id);
        return new ModelAndView("redirect:/product");
    }


}
