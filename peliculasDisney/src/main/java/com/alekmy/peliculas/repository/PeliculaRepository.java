package com.alekmy.peliculas.repository;

import com.alekmy.peliculas.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long> {
    
    
}
