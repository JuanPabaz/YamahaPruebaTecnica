package com.yamaha.prueba.dto;

import com.yamaha.prueba.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Integer idUser;
    private String fullName;
    private Role role;
    private String username;

}