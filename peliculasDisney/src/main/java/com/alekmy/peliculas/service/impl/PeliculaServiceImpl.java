package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.mapper.PeliculaMapper;
import com.alekmy.peliculas.repository.PeliculaRepository;
import com.alekmy.peliculas.service.IPeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaRepository peliculaRepository;

    //SE USA EL TRANSACTIONAL????
    
    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDto2Entity(dto);
        PeliculaEntity peliculaSaved = peliculaRepository.save(peliculaEntity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2Dto(peliculaSaved, false, false); // ver como afecta acá el save. TODO: ver
        return result;

    }

    @Override
    public List<PeliculaDTO> getAllPeliculas() { 
        List<PeliculaEntity> peliculasEntity = peliculaRepository.findAll();
        List<PeliculaDTO> result = peliculaMapper.peliculaEntityList2DtoList(peliculasEntity, false);
        return result;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

}