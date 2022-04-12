package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.PeliculaDTO;
import java.util.List;

public interface IPeliculaService {
    
    public PeliculaDTO save (PeliculaDTO dto);
    
    public List<PeliculaDTO> getAllPeliculas();
    
    public void delete(Long id);
}
