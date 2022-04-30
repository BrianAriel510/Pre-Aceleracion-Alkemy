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

    private Integer edad;

    private Double peso;

    private String historia;
}
