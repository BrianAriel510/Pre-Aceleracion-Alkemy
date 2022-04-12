package com.alekmy.peliculas.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "generos")
@Getter
@Setter
@SQLDelete(sql = "UPDATE generos SET deleted = true WHERE id=?") // en la sentencia sql, en realidad hago un update
@Where(clause = "deleted=false") //--> soft Delete donde "lo doy de baja". Diferencio eliminados de no (1 o 0)
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private boolean deleted = Boolean.FALSE; //--> soft delete. Se complementa con las anotaciondes @SQL y @Where. 1(T) o 0(F) en la DB

    @OneToMany(mappedBy = "genero", cascade = CascadeType.PERSIST)
    private Set<PeliculaEntity> peliculasAsociadas;

}