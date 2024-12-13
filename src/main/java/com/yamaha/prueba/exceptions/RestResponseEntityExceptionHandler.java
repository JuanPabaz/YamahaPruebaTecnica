package com.yamaha.prueba.exceptions;

import com.yamaha.prueba.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCreateRequest.class)
    public ResponseEntity<Object> handleBadCreateRequest(BadCreateRequest exception) {
        ErrorMessageDTO message = new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> objectNotFoundException(ObjectNotFoundException exception){
        ErrorMessageDTO message = new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(BadUserCredentialsException.class)
    public ResponseEntity<ErrorMessageDTO> userNotRegisterException(BadUserCredentialsException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
