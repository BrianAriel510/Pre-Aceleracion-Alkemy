package com.alekmy.peliculas.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@SQLDelete(sql = "UPDATE peliculas SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;

    private String titulo;

    private String imagen;

    @Column(name = "fecha_de_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDeCreacion;

    private Integer calificacion;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "personajes_peliculas",
            joinColumns = @JoinColumn(name = "peliculas_id"),
            inverseJoinColumns = @JoinColumn(name = "personajes_id"))
    private List<PersonajeEntity> personajesAsociados = new ArrayList<>();

    @ManyToOne 
    @JoinColumn(name = "genero_id"/*, insertable = false, updatable = false*/)
    private GeneroEntity genero;

    private Boolean deleted = Boolean.FALSE;

    public void AddPersonaje(PersonajeEntity personje) {
        personajesAsociados.add(personje);
    }

    public void removePersonaje(PersonajeEntity personaje) {
        this.personajesAsociados.remove(personaje);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PeliculaEntity other = (PeliculaEntity) obj;
        if (!Objects.equals(this.idMovie, other.idMovie)) {
            return false;
        }
        return true;
    }

    }
