package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.PersonajeBasicDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import java.util.List;
import java.util.Set;

public interface IPersonajeService {
    
    public PersonajeDTO save(PersonajeBasicDTO dto);
    
    public PersonajeDTO save(PersonajeDTO dto);
    
    public List<PersonajeDTO> getAllPersonajes();
    
    public void delete(Long id);
    
    public PersonajeDTO update(PersonajeDTO dto, Long id);
    
    public PersonajeDTO findById(Long id);
       
}
