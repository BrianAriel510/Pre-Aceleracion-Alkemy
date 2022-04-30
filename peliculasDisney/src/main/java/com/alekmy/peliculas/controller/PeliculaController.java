package com.alekmy.peliculas.controller;

import com.alekmy.peliculas.dto.PeliculaDTO;
import com.alekmy.peliculas.exceptions.BadRequestException;
import com.alekmy.peliculas.service.IPeliculaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class PeliculaController {

    @Autowired
    private IPeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@Valid @RequestBody PeliculaDTO pelicula) {
        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updatePelicula(@Valid @RequestBody PeliculaDTO dto, @PathVariable Long id,
            Errors error) {

        if (error.hasErrors()) {
            throw new BadRequestException("Error en la carga del personaje");
        }

        PeliculaDTO result = peliculaService.update(dto, id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> AddCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {

        PeliculaDTO result = peliculaService.addCharacter(idMovie, idCharacter);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> removeCharacterInMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        
        PeliculaDTO result = peliculaService.removeCharacterInMovie(idMovie,idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<PeliculaDTO> basicDTOS = peliculaService.getByFilters(name, genre, order);
        return ResponseEntity.ok().body(basicDTOS);
    }
}
