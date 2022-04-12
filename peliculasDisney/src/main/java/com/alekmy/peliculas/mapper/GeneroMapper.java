package com.alekmy.peliculas.mapper;

import com.alekmy.peliculas.dto.GeneroBasicDTO;
import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.entity.GeneroEntity;
import com.alekmy.peliculas.entity.PeliculaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        generoDTO.setId(entity.getId()); //ahora si tengo el id y tengo que devolverlo al usuario.
        generoDTO.setNombre(entity.getNombre());
        if (loadPelicula) {
            Set<PeliculaDTO> peliculasDTO
                    = (Set<PeliculaDTO>) peliculaMapper.peliculaEntity2Dto(
                            (PeliculaEntity) entity.getPeliculasAsociadas(), false, false);
            generoDTO.setPeliculasAsociadas(peliculasDTO);
        }
        return generoDTO;
    }

    public GeneroBasicDTO generoEntity2DTOBasic(GeneroEntity entity) {
        GeneroBasicDTO generoDTO = new GeneroBasicDTO();
        generoDTO.setId(entity.getId()); //ahora si tengo el id y tengo que devolverlo al usuario.
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

    public List<GeneroBasicDTO> generoBasicEntityList2DTOList(List<GeneroEntity> listGeneroEntity) {
        List<GeneroBasicDTO> listGeneroDTO = new ArrayList<>();
        for (GeneroEntity entity : listGeneroEntity) {
            listGeneroDTO.add(generoEntity2DTOBasic(entity));
        }
        return listGeneroDTO;
    }
}
