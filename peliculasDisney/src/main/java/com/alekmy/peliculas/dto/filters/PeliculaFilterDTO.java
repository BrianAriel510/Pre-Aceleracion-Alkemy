package com.alekmy.peliculas.dto.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaFilterDTO {

    private String name;
    private Long genre;
    private String order;

    public PeliculaFilterDTO(String name, Long genre, String order) {
        this.name = name;
        this.genre = genre;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
