package com.alekmy.peliculas.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;

    private String nombre;

    private boolean deleted = Boolean.FALSE; //--> soft delete. Se complementa con las anotaciondes @SQL y @Where. 1(T) o 0(F) en la DB

}
