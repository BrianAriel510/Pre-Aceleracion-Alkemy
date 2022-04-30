package com.alekmy.peliculas.dto;

import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    @NotEmpty(message = "Por favor, ingrese un título para la película")
    private String titulo;
    private String imagen;

    @NotNull(message = "Por favor, ingrese una fecha")
    private String fechaDeCreacion; //tener en cuenta que la fecha acá tiene que es un String porque estoy en el DTO y voy a tener que parsearla en el mapper

    @Range(min = 1, max = 5, message = "La calificación debe ser entre 1 y 5")
    private Integer calificacion;

    private List<PersonajeDTO> personajesAsociados;
    private GeneroDTO genero;
}
