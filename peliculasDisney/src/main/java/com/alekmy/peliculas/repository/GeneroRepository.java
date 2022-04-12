package com.alekmy.peliculas.repository;

import com.alekmy.peliculas.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository <GeneroEntity, Long> {

}

