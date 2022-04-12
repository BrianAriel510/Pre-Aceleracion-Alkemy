package com.alekmy.peliculas.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeDTO {
    
    private Long id;
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;
    private Set<PeliculaDTO> peliculasAsociadas;
}
