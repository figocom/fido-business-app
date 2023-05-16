package com.figo.fidobusiness.service;

import com.figo.fidobusiness.domain.Supplier;
import com.figo.fidobusiness.dto.*;

import java.util.List;

public interface UserService extends BaseService<UserIdDTO, UserDTO> {
    List<ClientIdDTO> getAllClients();

    void addClient(ClientDTO clientDTO);

    void deleteClient(String id);

    List<SupplierIdDTO> getAllSuppliers();

    void addSupplier(SupplierDTO supplierDTO);

    void deleteSupplier(String id);

    Supplier getSupplierByID(String supplierId);

}
