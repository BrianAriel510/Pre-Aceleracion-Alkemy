package com.alekmy.peliculas.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PeliculaBasicDTO {

    private Long id;

    @NotEmpty(message = "Por favor, ingrese un título para la película")
    private String titulo;

    private String imagen;

    @NotNull(message = "Por favor, ingrese una fecha")
    private String fechaDeCreacion;

    @Range(min = 1, max = 5, message = "La calificación debe ser entre 1 y 5")
    private Integer calificacion;

}
