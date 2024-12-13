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
public class SalesRequestDTO {

    private LocalDate date;
    private String invoiceNumber;
    private String city;
    private String store;
    private Double price;
    private Long idVehicle;
    private Long idClient;
    private String salesPerson;
}
