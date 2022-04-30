package com.alekmy.peliculas.repository;

import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

    public List<PeliculaEntity> findAll(Specification<PeliculaEntity> specification);
}
