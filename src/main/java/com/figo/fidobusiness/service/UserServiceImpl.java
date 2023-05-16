package com.figo.fidobusiness.service;

import com.figo.fidobusiness.dao.AuthRoleDAO;
import com.figo.fidobusiness.dao.AuthUserDAO;
import com.figo.fidobusiness.dao.ClientDAO;
import com.figo.fidobusiness.dao.SupplierDAO;
import com.figo.fidobusiness.domain.AuthRole;
import com.figo.fidobusiness.domain.AuthUser;
import com.figo.fidobusiness.domain.Client;
import com.figo.fidobusiness.domain.Supplier;
import com.figo.fidobusiness.dto.*;
import com.figo.fidobusiness.mapper.AuthUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final AuthUserDAO userDAO;
    private final AuthUserMapper userMapper;
    private final AuthRoleDAO roleDAO;
    private final ClientDAO clientDAO;
    private final SupplierDAO supplierDAO;
    @Override
    public List<UserIdDTO> getAll() {
        List<AuthUser>users=userDAO.getAllUsers();
        users.forEach(user -> {
            Collection<AuthRole> roles=roleDAO.findAllByUserId(user.getId());
            user.setRoles(roles);
        });
        return userMapper.toDTOList(users);
    }

    @Override
    public void add(UserDTO userDto) {
       Collection<AuthRole>roles= roleDAO.findById(userDto.getRole());
       userDAO.save(userMapper.toEntity(userDto,roles));
    }

    @Override
    public void update(UserIdDTO userIdDTO) {
//        AuthUser entity =userMapper.toEntity(userIdDTO);
//       userDAO.update(entity);
    }

    @Override
    public void delete( String id) {
       userDAO.delete(Integer.parseInt(id));
    }

    @Override
    public UserIdDTO get(String id) {
        Optional<AuthUser>user=userDAO.getById(id);
        return user.map(userMapper::toDTO).orElse(null);

    }

    @Override
    public List<ClientIdDTO> getAllClients() {
        List<Client> clients=clientDAO.getAllClients();
        return userMapper.toClientDTOList(clients);
    }

    @Override
    public void addClient(ClientDTO clientDTO) {
        clientDAO.save(Client.builder().name(clientDTO.getClientName()).phone(clientDTO.getClientPhone()).build());

    }

    @Override
    public void deleteClient(String id) {
        clientDAO.delete(Integer.parseInt(id));
    }

    @Override
    public List<SupplierIdDTO> getAllSuppliers() {
        List<Supplier> suppliers=supplierDAO.getAllSuppliers();
        return userMapper.toSupplierDTOList(suppliers);
    }

    @Override
    public void addSupplier(SupplierDTO supplierDTO) {
        supplierDAO.save(Supplier.builder().name(supplierDTO.getSupplierName()).phone(supplierDTO.getSupplierPhone()).build());
    }

    @Override
    public void deleteSupplier(String id) {
        supplierDAO.delete(Integer.parseInt(id));
    }

    @Override
    public Supplier getSupplierByID(String supplierId) {
        return supplierDAO.getById(Integer.valueOf(supplierId));
    }
}
