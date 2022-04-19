package com.alekmy.peliculas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeBasicDTO {
 
    private Long id;
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;
}
