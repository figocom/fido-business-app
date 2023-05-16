package com.figo.fidobusiness.controller;

import com.figo.fidobusiness.dto.ClientIdDTO;
import com.figo.fidobusiness.dto.*;
import com.figo.fidobusiness.service.AuthRoleService;
import com.figo.fidobusiness.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/users")
@Controller
@PreAuthorize("hasAnyRole('DIRECTOR','ADMIN')")
@RequiredArgsConstructor
public class UserController {
    private final UserService usersService;
    private final AuthRoleService authRoleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('DIRECTOR','ADMIN')")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("user/users");
        List<UserIdDTO> users = usersService.getAll();
        List<RoleIdDTO> roleIdDTOS = authRoleService.getAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("roles", roleIdDTOS);
        modelAndView.addObject("usersDTO", new UserDTO());
        modelAndView.addObject("usersIdDto", new UserIdDTO());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public UserIdDTO get(@PathVariable String id){
        return usersService.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addUser(@Valid @ModelAttribute UserDTO usersDTO ) {
        usersService.add(usersDTO);
        return new ModelAndView("redirect:/users");
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateUser(@Valid @ModelAttribute UserIdDTO usersDTO) {
        usersService.update(usersDTO);
        return new ModelAndView("redirect:/users");
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView deleteUser( @PathVariable String id) {
        usersService.delete(id);
        return new ModelAndView("redirect:/users");
    }
    @GetMapping("/clients")
    @PreAuthorize("hasAnyRole('ADMIN','DIRECTOR')")
    public ModelAndView getClients() {
        ModelAndView modelAndView = new ModelAndView("user/clients");
        List<ClientIdDTO> clients = usersService.getAllClients();
        modelAndView.addObject("clientDTO", new ClientDTO());
        modelAndView.addObject("clients", clients);
        return modelAndView;
    }

    @PostMapping("/clients")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addClient(@Valid @ModelAttribute ClientDTO clientDTO ) {
        usersService.addClient(clientDTO);
        return new ModelAndView("redirect:/users/clients");
    }
    @GetMapping("/clients/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView deleteClient( @PathVariable String id) {
        usersService.deleteClient(id);
        return new ModelAndView("redirect:/users/clients");
    }
    @GetMapping("/suppliers")
    @PreAuthorize("hasAnyRole('ADMIN','DIRECTOR')")
    public ModelAndView getSuppliers() {
        ModelAndView modelAndView = new ModelAndView("user/suppliers");
        List<SupplierIdDTO> suppliers = usersService.getAllSuppliers();
        modelAndView.addObject("supplierDTO", new SupplierDTO());
        modelAndView.addObject("suppliers", suppliers);
        return modelAndView;
    }
    @PostMapping("/suppliers")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addSupplier(@Valid @ModelAttribute SupplierDTO supplierDTO ) {
        usersService.addSupplier(supplierDTO);
        return new ModelAndView("redirect:/users/suppliers");
    }
    @GetMapping("/suppliers/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView deleteSupplier( @PathVariable String id) {
        usersService.deleteSupplier(id);
        return new ModelAndView("redirect:/users/suppliers");
    }



}
