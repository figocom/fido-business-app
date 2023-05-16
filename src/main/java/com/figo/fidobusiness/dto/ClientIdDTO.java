package com.figo.fidobusiness.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientIdDTO {
    private String clientId;
    private String clientName;
    private String clientPhone;
}
