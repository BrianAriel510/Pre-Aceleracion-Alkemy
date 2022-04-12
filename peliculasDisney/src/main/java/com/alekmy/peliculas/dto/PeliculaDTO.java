package com.alekmy.peliculas.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;
    private String titulo;
    private String imagen;
    private String fechaDeCreacion; //tener en cuenta que la fecha acá tiene que es un String porque estoy en el DTO y voy a tener que parsearla en el mapper
    private Integer calificacion;
    private Set<PersonajeDTO> personajesAsociados; //TODO PersonajeDTO --> mismo aca, no trabajo con el entity
    private GeneroDTO genero;
    private Long generoId;
}
