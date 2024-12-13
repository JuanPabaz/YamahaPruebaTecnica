package com.yamaha.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VehicleRequestDTO {

    private String engineNumber;
    private String model;
    private Integer cylinderCapacity;
    private String color;
    private LocalDate dateOfAssembly;
    private Integer yearModel;
}
