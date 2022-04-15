package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PersonajeEntity;
import com.alekmy.peliculas.mapper.PersonajeMapper;
import com.alekmy.peliculas.repository.PersonajeRepository;
import com.alekmy.peliculas.service.IPersonajeService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personajeSaved, false, false);
        return result;
    }

    @Override
    public Set<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> personajesEntity = personajeRepository.findAll();
        Set<PersonajeEntity> hSetPersonajes = new HashSet<PersonajeEntity>(personajesEntity);
        Set<PersonajeDTO> result = personajeMapper.pesonajeListEntity2ListDTO(hSetPersonajes);
        return result;
    }

    @Override // TODO: preguntar antes si existe
    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO update(PersonajeDTO dto, Long id) {

        Optional<PersonajeEntity> personaje2Update = personajeRepository.findById(dto.getId());
        PersonajeEntity personaje = (PersonajeEntity) personajeRepository.save(personaje2Update);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personaje, true, true);
        return result;
    }

}
