package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import java.util.List;

public interface IPeliculaService {

    public PeliculaDTO save(PeliculaDTO dto);

    public List<PeliculaDTO> getAllPeliculas();

    public List<PeliculaBasicDTO> getAllPeliculasBasic();

    public void delete(Long id);

    public PeliculaDTO update(PeliculaDTO dto, Long id);

    public PeliculaDTO findById(Long id);

    public PeliculaEntity getPeliculaById(Long id);

    public PeliculaDTO addCharacter(Long id, Long idCharacter);

    public PeliculaDTO removeCharacterInMovie(Long id, Long idCharacter);

    public List<PeliculaDTO> getByFilters(String name, Long genre, String order);
}
