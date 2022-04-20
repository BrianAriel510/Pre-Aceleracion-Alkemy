package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.PersonajeBasicDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import com.alekmy.peliculas.mapper.PersonajeMapper;
import com.alekmy.peliculas.repository.PeliculaRepository;
import com.alekmy.peliculas.repository.PersonajeRepository;
import com.alekmy.peliculas.service.IPeliculaService;
import com.alekmy.peliculas.service.IPersonajeService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonajeServiceImpl implements IPersonajeService {
    
    @Autowired
    private PersonajeMapper personajeMapper;
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
    @Override
    public PersonajeDTO save(PersonajeBasicDTO dto) {
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personajeSaved, false);
        return result;
    }
    
    @Override
    /*Es correcto hacer este método? o me conviene buscar la forma de reutilizar el de arriba?
    Cambia el parámetro solamente. Uno recibe Basic y el otro un DTO full.*/
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personajeSaved, false);
        return result;
    }
    
    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> personajesEntity = personajeRepository.findAll();
        List<PersonajeEntity> listPersonajes = new ArrayList<>(personajesEntity);
        List<PersonajeDTO> result = personajeMapper.pesonajeListEntity2ListDTO(listPersonajes);
        
        return result;
    }
    
    @Override // TODO: preguntar antes si existe
    public void delete(Long id) {
        Optional<PersonajeEntity> pjFound = personajeRepository.findById(id);
        
        if (pjFound.isPresent()) {
            personajeRepository.deleteById(pjFound.get().getId());
        }
    }
    
    @Override
    public PersonajeDTO update(PersonajeDTO dto, Long id) {
        
        Optional<PersonajeEntity> personajeFound = personajeRepository.findById(id);
        
        if (!personajeFound.isPresent()) {
            log.info("El id " + id + " es incorrecto o inexistente");
        }
        
        PersonajeEntity personajeCaught = personajeFound.get();
        PersonajeDTO personaje2Update = personajeMapper.personaje2Update(personajeCaught, dto);
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(personaje2Update);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personajeEntity, false);
        result = this.save(result);
        return result;
    }
    
    @Override
    public PersonajeDTO findById(Long id) {
        Optional<PersonajeEntity> personajeFound = personajeRepository.findById(id);
        
        if (!personajeFound.isPresent()) {
            log.info("No se encontró el personaje con el ud " + id);
        }
        
        PersonajeDTO personaje = personajeMapper.personajeEntity2DTO(personajeFound.get(), true);
        return personaje;
    }
    
    @Override
    public PersonajeEntity getPersonajeById(Long id) {
    
        PersonajeEntity entity = personajeRepository.getById(id);
        return entity;
    }

}
