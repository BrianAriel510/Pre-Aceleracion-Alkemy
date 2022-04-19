package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.PersonajeBasicDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PersonajeEntity;
import com.alekmy.peliculas.mapper.PersonajeMapper;
import com.alekmy.peliculas.repository.PersonajeRepository;
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
        Optional<PersonajeEntity> pj2delete = personajeRepository.findById(id);

        if (pj2delete.isPresent()) {
            personajeRepository.deleteById(id);
        }
    }

    @Override
    public PersonajeDTO update(PersonajeDTO dto, Long id) {

        Optional<PersonajeEntity> personajeFound = personajeRepository.findById(id);

        if (!personajeFound.isPresent()) {
            log.info("El id es incorrecto");
        }
        
        PersonajeEntity personajeCaught = personajeFound.get();
        PersonajeDTO personaje2Update = personajeMapper.personaje2Update(personajeCaught, dto);
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(personaje2Update);
        PersonajeDTO result= personajeMapper.personajeEntity2DTO(personajeEntity, false);
        return result;
        
    }

}
