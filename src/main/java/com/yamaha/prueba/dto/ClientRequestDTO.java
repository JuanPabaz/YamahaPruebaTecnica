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
public class ClientRequestDTO {

    private String identificationNumber;
    private String names;
    private String lastName;
    private String address;
    private LocalDate dateOfBirth;
    private String gender;
    private String cellPhone;
    private String email;
    private Long idUser;
}
