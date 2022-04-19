package com.alekmy.peliculas.mapper;

import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMapper {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private PersonajeMapper personajeMapper;

    public PeliculaEntity peliculaDto2Entity(PeliculaDTO dto) {
        PeliculaEntity peliculaEntity = new PeliculaEntity();
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setFechaDeCreacion(string2LocalDate(dto.getFechaDeCreacion()));
        peliculaEntity.setCalificacion(dto.getCalificacion());
        return peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2Dto(PeliculaEntity entity, boolean loadGenero, boolean loadPersonajes) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(entity.getId());
        peliculaDTO.setTitulo(entity.getTitulo());
        peliculaDTO.setImagen(entity.getImagen());
        peliculaDTO.setFechaDeCreacion(entity.getFechaDeCreacion().toString()); //tener en cuenta de parsear la fecha
        peliculaDTO.setCalificacion(entity.getCalificacion());
        if (loadGenero) {
            GeneroDTO generoDTO = generoMapper.generoEntity2DTO(entity.getGenero(), false);
            peliculaDTO.setGenero(generoDTO);
        }

        if (loadPersonajes) {
            List<PersonajeDTO> personajesDTO = personajeMapper.pesonajeListEntity2ListDTO(entity.getPersonajesAsociados());
            peliculaDTO.setPersonajesAsociados(personajesDTO);
        }
        return peliculaDTO;
    }
    
     public PeliculaBasicDTO peliculaEntity2DtoBasic(PeliculaEntity entity) {
        PeliculaBasicDTO peliculaDTO = new PeliculaBasicDTO();
        peliculaDTO.setId(entity.getId());
        peliculaDTO.setTitulo(entity.getTitulo());
        peliculaDTO.setImagen(entity.getImagen());
        peliculaDTO.setFechaDeCreacion(entity.getFechaDeCreacion().toString()); 
        peliculaDTO.setCalificacion(entity.getCalificacion());
        return peliculaDTO;
     }

    public List<PeliculaDTO> peliculaEntityList2DtoList(List<PeliculaEntity> listPeliculaEntity, boolean loadGenero, boolean loadPersonajes) {
        List<PeliculaDTO> listPeliculaDTO = new ArrayList<>();
        for (PeliculaEntity entity : listPeliculaEntity) {
            listPeliculaDTO.add(peliculaEntity2Dto(entity, false, false));
        }
        return listPeliculaDTO;
    }

    public List<PeliculaBasicDTO> peliculaBasicEntityList2DtoList(List<PeliculaEntity> listPeliculaEntity) {
        List<PeliculaBasicDTO> listPeliculaDTO = new ArrayList<>();
        for (PeliculaEntity entity : listPeliculaEntity) {
            listPeliculaDTO.add(peliculaEntity2DtoBasic(entity));
        }
        return listPeliculaDTO;
    }

    public List<PeliculaEntity> peliculaDTOList2EntityList(List<PeliculaDTO> listPeliculaDTO) { //TODO: cargar boolean de genero
        List<PeliculaEntity> entities = new ArrayList<>();
        for (PeliculaDTO dto : listPeliculaDTO) {
            entities.add(this.peliculaDto2Entity(dto));
        }
        return (List<PeliculaEntity>) entities;
    }

    public LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

}
