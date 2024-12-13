package com.yamaha.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SalesAnalysisDTO {

    private Long idCliente;
    private String name;
    private String lastName;
    private List<VehicleResponseDTO> vehicles;
    private Double periodicity;
    private LocalDate nextSaleEstimate;
}
