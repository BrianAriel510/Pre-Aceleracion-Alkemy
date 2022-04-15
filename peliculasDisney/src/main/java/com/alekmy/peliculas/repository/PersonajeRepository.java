package com.alekmy.peliculas.repository;

import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository <PersonajeEntity,Long> {
    
    public PersonajeRepository save(Optional<PersonajeEntity> personaje);
}

