package com.inventus.controller;

import com.inventus.infra.exception.ErrorResponse;
import com.inventus.infra.exception.ValidarCadastroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ValidarCadastroException.class)
    public ResponseEntity<ErrorResponse> handlerValidarCadastro(ValidarCadastroException validarCadastroException) {

        ErrorResponse errorResponse = new ErrorResponse(validarCadastroException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

}