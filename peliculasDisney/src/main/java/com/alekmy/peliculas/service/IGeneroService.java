package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.GeneroBasicDTO;
import com.alekmy.peliculas.dto.GeneroDTO;
import java.util.List;

public interface IGeneroService {
    
    public GeneroDTO save (GeneroDTO dto);
    
    public List<GeneroDTO> getAllGeneros();
    
    public List<GeneroBasicDTO> getAllGenerosBasic();
    
    public void delete(Long id);
}
