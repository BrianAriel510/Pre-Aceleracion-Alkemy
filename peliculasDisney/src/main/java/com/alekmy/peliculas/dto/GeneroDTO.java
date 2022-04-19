package com.alekmy.peliculas.dto;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroDTO {

    private Long id;
    private String nombre;
    private List<PeliculaDTO> peliculasAsociadas;
}