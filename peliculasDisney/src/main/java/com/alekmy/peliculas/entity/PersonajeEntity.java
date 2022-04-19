package com.alekmy.peliculas.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "personajes")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personajes SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")// --> soft Delete 
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajesAsociados", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PeliculaEntity> peliculasAsociadas = new ArrayList<>();

    private boolean deleted = Boolean.FALSE; //--> soft delete
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
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
        final PersonajeEntity other = (PersonajeEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
