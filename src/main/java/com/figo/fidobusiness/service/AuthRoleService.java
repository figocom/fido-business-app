package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.AuthRoleDAO;
import com.figo.fidobusiness.domain.AuthRole;
import com.figo.fidobusiness.dto.RoleIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthRoleService {
    private final AuthRoleDAO authRoleDAO;

    public List<RoleIdDTO> getAll() {
        List<AuthRole> roles=authRoleDAO.findAll();
        List<RoleIdDTO> roleIdDTOS = new ArrayList<>();
        roles.forEach(authRole -> roleIdDTOS.add(new RoleIdDTO(authRole.getId(), authRole.getName())));
        return roleIdDTOS;
    }
}
