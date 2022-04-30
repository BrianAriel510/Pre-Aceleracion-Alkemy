package com.alekmy.peliculas.mapper;

import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.entity.GeneroEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public GeneroEntity generoDto2Entity(GeneroDTO dto) {
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setNombre(dto.getNombre());
        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity, boolean loadPelicula) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(entity.getIdGenero()); //ahora si tengo el id y tengo que devolverlo al usuario.
        generoDTO.setNombre(entity.getNombre());
      
        return generoDTO;
    }

    public GeneroDTO generoEntity2DTOBasic(GeneroEntity entity) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(entity.getIdGenero()); //ahora si tengo el id y tengo que devolverlo al usuario.
        generoDTO.setNombre(entity.getNombre());

        return generoDTO;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> listGeneroEntities, boolean loadPeliculas) {
        List<GeneroDTO> listGeneroDTO = new ArrayList<>();
        for (GeneroEntity entity : listGeneroEntities) {
            listGeneroDTO.add(generoEntity2DTO(entity, false));
        }
        return listGeneroDTO;
    }

    public List<GeneroDTO> generoBasicEntityList2DTOList(List<GeneroEntity> listGeneroEntity) {
        List<GeneroDTO> listGeneroDTO = new ArrayList<>();
        for (GeneroEntity entity : listGeneroEntity) {
            listGeneroDTO.add(generoEntity2DTOBasic(entity));
        }
        return listGeneroDTO;
    }
}
