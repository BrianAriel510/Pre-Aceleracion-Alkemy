package com.alekmy.peliculas.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroDTO {

    private Long id;
   
    @NotEmpty(message = "Por favor, ingrese un nombre")
    private String nombre;
}