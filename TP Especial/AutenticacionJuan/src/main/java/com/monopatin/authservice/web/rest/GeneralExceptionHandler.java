package com.monopatin.authservice.web.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.autenticacion.service.exception.user.ErrorDTO;
import com.example.autenticacion.service.exception.user.NotFoundException;

@RestControllerAdvice( basePackages = "com.monopatin.authservice.web.rest" )
public class GeneralExceptionHandler {

    @ExceptionHandler( NotFoundException.class )
    public ErrorDTO getException( NotFoundException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}
