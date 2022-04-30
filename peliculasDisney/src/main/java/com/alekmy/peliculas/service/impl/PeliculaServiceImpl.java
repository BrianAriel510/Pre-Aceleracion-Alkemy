package com.alekmy.peliculas.service.impl;

import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.dto.filters.PeliculaFilterDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import com.alekmy.peliculas.exceptions.ParamNotFoundException;
import com.alekmy.peliculas.mapper.PeliculaMapper;
import com.alekmy.peliculas.repository.PeliculaRepository;
import com.alekmy.peliculas.repository.PersonajeRepository;
import com.alekmy.peliculas.repository.specifications.PeliculaSpecification;
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
    private PeliculaSpecification peliculaSpecification;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDto2Entity(dto);
        PeliculaEntity peliculaSaved = peliculaRepository.save(peliculaEntity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2Dto(peliculaSaved, true, true); // ver como afecta acá el save. TODO: ver
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
    public PeliculaDTO addCharacter(Long id, Long idCharacter) {

        Optional<PeliculaEntity> peliculaFound = peliculaRepository.findById(id);
        if (!peliculaFound.isPresent()) {
            throw new ParamNotFoundException("Id " + id + " de la pelicula, no es válido");
        }

        Optional<PersonajeEntity> personajeFound = personajeRepository.findById(id);
        if (!personajeFound.isPresent()) {
            throw new ParamNotFoundException("Id " + id + " del personaje, no es válido");
        }

        peliculaFound.get().getPersonajesAsociados().add(personajeFound.get());

        PeliculaEntity peliculaEntity = peliculaRepository.save(peliculaFound.get());
        PeliculaDTO result = peliculaMapper.peliculaEntity2Dto(peliculaEntity, true, true);
        return result;
    }

    @Override
    public PeliculaDTO removeCharacterInMovie(Long id, Long idCharacter) {

        Optional<PeliculaEntity> peliculaFound = peliculaRepository.findById(id);
        if (!peliculaFound.isPresent()) {
            throw new ParamNotFoundException("Id " + id + " de la pelicula, no es válido");
        }

        Optional<PersonajeEntity> personajeFound = personajeRepository.findById(id);
        if (!personajeFound.isPresent()) {
            throw new ParamNotFoundException("Id " + id + " del personaje, no es válido");
        }

        peliculaFound.get().getPersonajesAsociados().remove(personajeFound.get());
        
        PeliculaEntity peliculaEntity = peliculaRepository.save(peliculaFound.get());
        PeliculaDTO result = peliculaMapper.peliculaEntity2Dto(peliculaEntity, true, true);
        return result;
    }

    @Override
    public List<PeliculaDTO> getByFilters(String name, Long genre, String order) {

        PeliculaFilterDTO filterDTO = new PeliculaFilterDTO(name, genre, order);

        List<PeliculaEntity> peliculas = peliculaRepository.findAll(peliculaSpecification.getByFilters(filterDTO));
        List<PeliculaDTO> dtos = peliculaMapper.peliculaEntityList2DtoList(peliculas, true, true);
        return dtos;
    }

    @Override
    public PeliculaDTO update(PeliculaDTO dto, Long id) {
        Optional<PeliculaEntity> peliculaFound = peliculaRepository.findById(id);

        if (!peliculaFound.isPresent()) {
            throw new ParamNotFoundException("Id " + id + " no válido");
        }

        PeliculaEntity peliculaCaught = peliculaFound.get();
        PeliculaDTO pelicula2Update = peliculaMapper.pelicula2Update(peliculaCaught, dto);
        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDto2Entity(pelicula2Update);
        PeliculaDTO result = peliculaMapper.peliculaEntity2Dto(peliculaEntity, true, true);
        result = this.save(result);
        return result;
    }

}
