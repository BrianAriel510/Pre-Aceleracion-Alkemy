package com.alekmy.peliculas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaBasicDTO {

    private Long id;
    private String titulo;
    private String imagen;    
    private String fechaDeCreacion;
    private Integer calificacion;

}

