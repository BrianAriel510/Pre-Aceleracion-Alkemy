
package com.alekmy.peliculas.controller;

import com.alekmy.peliculas.dto.ApiErrorDTO;
import com.alekmy.peliculas.exceptions.BadGatewayException;
import com.alekmy.peliculas.exceptions.BadRequestException;
import com.alekmy.peliculas.exceptions.ConflictException;
import com.alekmy.peliculas.exceptions.ForbiddenException;
import com.alekmy.peliculas.exceptions.NotFoundException;
import com.alekmy.peliculas.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler{

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({BadGatewayException.class})
    @ResponseBody
    public ApiErrorDTO badGateway(Exception exception) {
        return new ApiErrorDTO(exception, HttpStatus.BAD_GATEWAY.value());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.server.ServerWebInputException.class})
    @ResponseBody
    public ApiErrorDTO badRequest(Exception exception) {
        return new ApiErrorDTO(exception, HttpStatus.BAD_REQUEST.value());
    }
   
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ApiErrorDTO conflict(Exception exception) {
        return new ApiErrorDTO(exception, HttpStatus.CONFLICT.value());
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ApiErrorDTO fatalError(Exception exception) { 
        return new ApiErrorDTO(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    @ResponseBody
    public ApiErrorDTO forbidden(Exception exception) {
        return new ApiErrorDTO(exception, HttpStatus.FORBIDDEN.value());
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ApiErrorDTO notFoundRequest(Exception exception) {
        return new ApiErrorDTO(exception, HttpStatus.NOT_FOUND.value());
    }
   
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public void unauthorizedRequest() {
       
    }

    
}
