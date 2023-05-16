package com.figo.fidobusiness.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class MeasurementIdDTO {
    private String id;
    private String nameUz;
    private String nameRu;
}
