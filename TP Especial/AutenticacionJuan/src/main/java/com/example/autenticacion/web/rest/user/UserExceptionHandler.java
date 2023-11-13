package com.example.autenticacion.web.rest.user;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.autenticacion.service.exception.user.ErrorDTO;
import com.example.autenticacion.service.exception.user.UserException;

@RestControllerAdvice( basePackages = "com.monopatin.authservice.web.rest.USER" )
public class UserExceptionHandler {

    @ExceptionHandler( UserException.class )
    public ErrorDTO getUserException( UserException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}
