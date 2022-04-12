package com.alekmy.peliculas.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroDTO {

    private Long id;
    private String nombre;
    private Set<PeliculaDTO> peliculasAsociadas;
}