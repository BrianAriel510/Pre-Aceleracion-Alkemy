package com.alekmy.peliculas.mapper;

import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.dto.PersonajeBasicDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMapper {
    
    @Autowired
    private PeliculaMapper peliculaMapper;
    
    public PersonajeEntity personajeDTO2Entity(PersonajeBasicDTO dto) {
        PersonajeEntity personajeEntity = new PersonajeEntity();
        
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());        
        personajeEntity.setHistoria(dto.getHistoria());
        return personajeEntity;
    }
    
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = new PersonajeEntity();
        if (personajeEntity.getIdCharacter() == null) {
            personajeEntity.setIdCharacter(dto.getId());
        }
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        return personajeEntity;
    }
    
    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPelicula) {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(entity.getIdCharacter());
        personajeDTO.setNombre(entity.getNombre());
        personajeDTO.setImagen(entity.getImagen());
        personajeDTO.setEdad(entity.getEdad());
        personajeDTO.setPeso(entity.getPeso());
        personajeDTO.setHistoria(entity.getHistoria());
        if (loadPelicula) {
            List<PeliculaDTO> peliculasDTO
                    =  peliculaMapper.peliculaEntityList2DtoList(entity.getPeliculasAsociadas(), false, false);
            personajeDTO.setPeliculasAsociadas(peliculasDTO);
        }
        return personajeDTO;
    }
    
    public List<PersonajeDTO> pesonajeListEntity2ListDTO(List<PersonajeEntity> peliculasAsociadas, boolean loadPelicula) {
        List<PersonajeDTO> listPersonajeDTO = new ArrayList<>();
        for (PersonajeEntity entity : peliculasAsociadas) {
            listPersonajeDTO.add(personajeEntity2DTO(entity, loadPelicula));
        }
        return listPersonajeDTO;
    }
    
    public PersonajeDTO personaje2Update(PersonajeEntity entity, PersonajeDTO dto) {
        PersonajeDTO personajeDto = new PersonajeDTO();
        PersonajeDTO entityDto = personajeEntity2DTO(entity, false);
        personajeDto.setId(entityDto.getId());
        
        
        if (dto.getNombre() == null) {
            personajeDto.setNombre(entityDto.getNombre());
        } else {
            personajeDto.setNombre(dto.getNombre());
        }
        
        if (dto.getImagen() == null) {
            personajeDto.setImagen(entityDto.getImagen());
        } else {
            personajeDto.setImagen(dto.getImagen());
        }
        
        if (dto.getEdad() == null) {
            personajeDto.setEdad(entityDto.getEdad());
        } else {
            personajeDto.setEdad(dto.getEdad());
        }
        
        if (dto.getPeso() == null) {
            personajeDto.setPeso(entityDto.getPeso());
        } else {
            personajeDto.setPeso(dto.getPeso());
        }
        
        if (dto.getHistoria() == null) {
            personajeDto.setHistoria(entityDto.getHistoria());
        } else {
            personajeDto.setHistoria(dto.getHistoria());
        }
        
        return personajeDto;
    }
    
}
