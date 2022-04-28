package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.dto.PersonajeBasicDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.List;
import java.util.Set;

public interface IPersonajeService {
    
    public PersonajeDTO save(PersonajeBasicDTO dto);
    
    public PersonajeDTO save(PersonajeDTO dto);
    
    public List<PersonajeDTO> getAllPersonajes();
    
    public void delete(Long id);
    
    public PersonajeDTO update(PersonajeDTO dto, Long id);
    
    public PersonajeDTO findById(Long id);
    
    public PersonajeEntity getPersonajeById(Long id);
       
    public List<PersonajeDTO> getByFilters(String name, Integer age, List<Long> movies/*, String order*/);
}
