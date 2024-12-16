package com.inventus.controller;

import com.inventus.infra.exception.AutenticacaoException;
import com.inventus.infra.exception.CredenciaisInvalidasException;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<ErrorResponse> handlerAutenticacao(AutenticacaoException autenticacaoException) {

        ErrorResponse errorResponse = new ErrorResponse(autenticacaoException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<ErrorResponse> handlerCredenciaisInvalidas(CredenciaisInvalidasException credenciaisInvalidasException) {

        ErrorResponse errorResponse = new ErrorResponse(credenciaisInvalidasException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

}