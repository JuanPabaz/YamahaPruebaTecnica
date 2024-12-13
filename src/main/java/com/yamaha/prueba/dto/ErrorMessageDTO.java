package com.yamaha.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDTO {
    private Integer statusCode;
    private HttpStatus status;
    private String message;
}
