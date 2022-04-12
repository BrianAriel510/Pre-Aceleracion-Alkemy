package com.alekmy.peliculas.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@SQLDelete(sql = "UPDATE icon SET delete = true WHERE id=?")
@Where(clause = "deleted=false")// --> soft Delete 
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String imagen;

    private String nombre;

    private Integer edad;

    private Double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajesAsociados", cascade = CascadeType.PERSIST)
    private Set<PeliculaEntity> peliculasAsociadas = new HashSet<>();

    private boolean deleted = Boolean.FALSE; //--> soft delete

}