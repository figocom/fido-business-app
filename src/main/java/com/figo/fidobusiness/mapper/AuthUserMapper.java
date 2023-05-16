package com.figo.fidobusiness.mapper;

import com.figo.fidobusiness.domain.AuthRole;
import com.figo.fidobusiness.domain.AuthUser;
import com.figo.fidobusiness.domain.Client;
import com.figo.fidobusiness.domain.Supplier;
import com.figo.fidobusiness.dto.ClientIdDTO;
import com.figo.fidobusiness.dto.SupplierIdDTO;
import com.figo.fidobusiness.dto.UserDTO;
import com.figo.fidobusiness.dto.UserIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@RequiredArgsConstructor
public class AuthUserMapper {
    private final PasswordEncoder passwordEncoder;
    public UserIdDTO toDTO(AuthUser authUser) {
        return UserIdDTO.builder().id(String.valueOf(authUser.getId()))
                        .name(authUser.getFirstName())
                        .surname(authUser.getLastName())
                        .phoneNumber(authUser.getPhone()).
                        role(authUser.getRoles().stream().map(AuthRole::getName).collect(Collectors.toList())).
                build();
    }
    public List<UserIdDTO> toDTOList(List<AuthUser> users) {
        return users.stream().map(this::toDTO).toList();
    }
    public AuthUser toEntity(UserDTO userDTO , Collection<AuthRole> roles) {
        return AuthUser.builder().firstName(userDTO.getName()).lastName(userDTO.getSurname()).roles(roles).phone(userDTO.getPhoneNumber())
                .username(userDTO.getPhoneNumber()).password(passwordEncoder.encode(userDTO.getName())).build();

    }
    public ClientIdDTO toDTO(Client client) {
        return ClientIdDTO.builder().clientId(String.valueOf(client.getId()))
                .clientName(client.getName())
                .clientPhone(client.getPhone()).
                        build();

    }
    public List<ClientIdDTO> toClientDTOList(List<Client> clients) {
        return clients.stream().map(this::toDTO).toList();
    }

    public SupplierIdDTO toSupplierDTO(Supplier supplier) {
        return SupplierIdDTO.builder().supplierId(String.valueOf(supplier.getId()))
                .supplierName(supplier.getName())
                .supplierPhone(supplier.getPhone()).
                        build();
    }

    public List<SupplierIdDTO> toSupplierDTOList(List<Supplier> suppliers) {
        return suppliers.stream().map(this::toSupplierDTO).toList();
    }
}
