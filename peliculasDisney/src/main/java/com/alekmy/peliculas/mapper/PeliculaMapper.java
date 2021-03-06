package com.alekmy.peliculas.mapper;

import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import static com.alekmy.peliculas.mapper.util.String2LocalDate.string2LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        peliculaEntity.setGenero(generoMapper.generoDto2Entity(dto.getGenero()));
        return peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2Dto(PeliculaEntity entity, boolean loadGenero, boolean loadPersonajes) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(entity.getIdMovie());
        peliculaDTO.setTitulo(entity.getTitulo());
        peliculaDTO.setImagen(entity.getImagen());
        peliculaDTO.setFechaDeCreacion(entity.getFechaDeCreacion().toString()); //tener en cuenta de parsear la fecha
        peliculaDTO.setCalificacion(entity.getCalificacion());
        if (loadGenero) {
            GeneroDTO generoDTO = generoMapper.generoEntity2DTO(entity.getGenero(), false);
            peliculaDTO.setGenero(generoDTO);
        }

        if (loadPersonajes) {
            List<PersonajeDTO> personajesDTO = personajeMapper.pesonajeListEntity2ListDTO(entity.getPersonajesAsociados(), false);
            peliculaDTO.setPersonajesAsociados(personajesDTO);
        }
        return peliculaDTO;
    }

    public PeliculaBasicDTO peliculaEntity2DtoBasic(PeliculaEntity entity) {
        PeliculaBasicDTO peliculaDTO = new PeliculaBasicDTO();
        peliculaDTO.setId(entity.getIdMovie());
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
    
    public PeliculaDTO pelicula2Update(PeliculaEntity entity, PeliculaDTO dto ){
        PeliculaDTO peliculaDto = new PeliculaDTO();
        PeliculaDTO entityDto = peliculaEntity2Dto(entity, false, false);
        peliculaDto.setId(entityDto.getId());
        
        if (dto.getTitulo() == null) {
            peliculaDto.setTitulo(entityDto.getTitulo());
        } else {
            peliculaDto.setTitulo(dto.getTitulo());
        }
        
        if (dto.getImagen() == null) {
            peliculaDto.setImagen(entityDto.getImagen());
        } else {
            peliculaDto.setImagen(dto.getImagen());
        }
        
        if (dto.getFechaDeCreacion() == null) {
            peliculaDto.setFechaDeCreacion(entityDto.getFechaDeCreacion());
        } else {
            peliculaDto.setFechaDeCreacion(dto.getFechaDeCreacion());
        }
        
        if (dto.getCalificacion() == null) {
            peliculaDto.setCalificacion(entityDto.getCalificacion());
        } else {
            peliculaDto.setCalificacion(dto.getCalificacion());
        }
        
        return peliculaDto;
    }
}
