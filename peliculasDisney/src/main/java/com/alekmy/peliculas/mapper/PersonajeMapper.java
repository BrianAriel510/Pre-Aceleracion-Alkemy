package com.alekmy.peliculas.mapper;

import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;
    
       
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        Set<PeliculaEntity> peliculasEntity = (Set<PeliculaEntity>) peliculaMapper.peliculaDTOList2EntityList((List<PeliculaDTO>) dto.getPeliculasAsociadas());
        personajeEntity.setPeliculasAsociadas(peliculasEntity);
        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPelicula, boolean loadGenero) {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(entity.getId());
        personajeDTO.setNombre(entity.getNombre());
        personajeDTO.setImagen(entity.getImagen());
        personajeDTO.setEdad(entity.getEdad());
        personajeDTO.setPeso(entity.getPeso());
        personajeDTO.setHistoria(entity.getHistoria());
        if (loadPelicula) {
            Set<PeliculaDTO> peliculasDTO = 
                    (Set<PeliculaDTO>) peliculaMapper.peliculaEntity2Dto(
                            (PeliculaEntity) entity.getPeliculasAsociadas(), false, false); // casteo la List porque en verdad estoy usando un Set
            personajeDTO.setPeliculasAsociadas(peliculasDTO);
        }
        return personajeDTO;
    }

    public List<PersonajeDTO> pesonajeListEntity2ListDTO(List<PersonajeEntity> peliculasAsociadas) {
        List<PersonajeDTO> listPersonajeDTO = new ArrayList<>();
        for (PersonajeEntity entity : peliculasAsociadas) {
            listPersonajeDTO.add(personajeEntity2DTO(entity, false, false));
        }
        return listPersonajeDTO;
    }
    
   
}
