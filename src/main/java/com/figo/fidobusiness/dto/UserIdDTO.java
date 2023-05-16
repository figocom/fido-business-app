package com.figo.fidobusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIdDTO {
    private String id;
    private String name;
    private String surname;
    private Collection<String> role;
    private String phoneNumber;
}
