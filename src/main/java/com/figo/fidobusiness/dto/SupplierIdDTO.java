package com.figo.fidobusiness.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierIdDTO {
    private String supplierId;
    private String supplierName;
    private String supplierPhone;
}
