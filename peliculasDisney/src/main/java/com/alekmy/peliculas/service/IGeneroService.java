package com.alekmy.peliculas.service;

import com.alekmy.peliculas.dto.GeneroDTO;
import java.util.List;

public interface IGeneroService {
    
    public GeneroDTO save (GeneroDTO dto);
    
    public List<GeneroDTO> getAllGeneros();
    
    public void delete(Long id);
}
