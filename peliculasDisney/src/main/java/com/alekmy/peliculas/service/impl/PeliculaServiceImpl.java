package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import com.alekmy.peliculas.mapper.PeliculaMapper;
import com.alekmy.peliculas.repository.PeliculaRepository;
import com.alekmy.peliculas.service.IPeliculaService;
import com.alekmy.peliculas.service.IPersonajeService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PeliculaServiceImpl implements IPeliculaService {

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private IPersonajeService personajeService;

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
        List<PeliculaDTO> result = peliculaMapper.peliculaEntityList2DtoList(peliculasEntity, false, false);
        return result;
    }

    @Override
    public List<PeliculaBasicDTO> getAllPeliculasBasic() {
        List<PeliculaEntity> peliculaEntity = peliculaRepository.findAll();
        List<PeliculaBasicDTO> result = peliculaMapper.peliculaBasicEntityList2DtoList(peliculaEntity);
        return result;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO findById(Long id) {
        Optional<PeliculaEntity> peliculaFound = peliculaRepository.findById(id);
        if (!peliculaFound.isPresent()) {
            log.info("No se encontró una película con el id" + id);
        }
        PeliculaEntity peliculaCaught = peliculaFound.get();
        PeliculaDTO result = peliculaMapper.peliculaEntity2Dto(peliculaCaught, false, false);
        return result;
    }

    @Override
    public PeliculaEntity getPeliculaById(Long id) {

        PeliculaEntity entity = peliculaRepository.getById(id);
        return entity;
    }

    @Override
    public void addCharacter(Long id, Long idCharacter) {

        PeliculaEntity pelicula = this.peliculaRepository.getById(id);
        pelicula.getPersonajesAsociados().size();
        PersonajeEntity personaje = personajeService.getPersonajeById(idCharacter);
        pelicula.AddPersonaje(personaje);
        peliculaRepository.save(personaje);
    }

}
