
package com.alekmy.peliculas.controller;

import com.alekmy.peliculas.dto.PeliculaBasicDTO;
import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.service.IPeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {
    
    @Autowired
    private IPeliculaService peliculaService;
    
    @PostMapping
    public ResponseEntity<PeliculaDTO> save (@RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }
    
    @GetMapping("/all")// ME DEVUELVE SOLO LAS PELICULAS (BASIC)
    public ResponseEntity<List<PeliculaDTO>> getAllBasic(){
        List<PeliculaDTO> peliculaList = peliculaService.getAllPeliculas();
        return ResponseEntity.ok().body(peliculaList);
    }
    
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@RequestParam Long id){
       peliculaService.delete(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
