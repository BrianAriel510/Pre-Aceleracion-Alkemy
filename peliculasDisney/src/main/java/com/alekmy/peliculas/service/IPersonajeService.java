package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.PersonajeDTO;
import java.util.Set;

public interface IPersonajeService {
    
    public PersonajeDTO save(PersonajeDTO dto);
    
    public Set<PersonajeDTO> getAllPersonajes();
    
    public void delete(Long id);
}
