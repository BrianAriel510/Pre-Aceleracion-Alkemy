package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import java.util.List;

public interface IPeliculaService {
    
    public PeliculaDTO save (PeliculaDTO dto);
    
    public List<PeliculaDTO> getAllPeliculas();
    
    public List<PeliculaBasicDTO> getAllPeliculasBasic();
    
    public void delete(Long id);
    
    public PeliculaDTO findById(Long id);
    
    public PeliculaEntity getPeliculaById(Long id);
    
    public void addCharacter(Long id, Long idCharacter);
}
