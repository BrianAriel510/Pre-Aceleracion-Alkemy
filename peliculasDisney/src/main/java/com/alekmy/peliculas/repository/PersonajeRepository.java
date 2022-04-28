package com.alekmy.peliculas.repository;

import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository <PersonajeEntity,Long>, JpaSpecificationExecutor<PersonajeEntity> {
    
    public PersonajeRepository save(Optional<PersonajeEntity> personaje);
    
    public List<PersonajeEntity> findAll(Specification<PersonajeEntity> specification);
}

