package com.alekmy.peliculas.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeBasicDTO {

    private Long id;

    @NotEmpty(message = "Por favor, ingrese un nombre")
    private String nombre;

    private String imagen;

    @NotEmpty(message = "Debe ingresar una edad")
    @Min(value = 0, message = "La edad no puede ser menor que 0")
    private Integer edad;

    @NotEmpty(message = "Debe ingresar un peso")
    @Min(value = 0, message = "El peso no puede ser inferior a 0")
    private Double peso;

    private String historia;
}
