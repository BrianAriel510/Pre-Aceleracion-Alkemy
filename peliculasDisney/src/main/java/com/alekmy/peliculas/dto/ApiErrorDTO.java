package com.alekmy.peliculas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorDTO {

    private String error;
    private String message;
    private Integer code;

    public ApiErrorDTO(Exception exception, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.code = code;
    }

}
