package com.alekmy.peliculas.controller;

import com.alekmy.peliculas.dto.PersonajeDTO;
import com.alekmy.peliculas.service.IPersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personajes")
public class PersonajeController {
    
    @Autowired
    private IPersonajeService personajeService;
    
    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO dto){
        PersonajeDTO personajeGuardado = personajeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<PersonajeDTO>> getAll(){
        List<PersonajeDTO> personajes = (List<PersonajeDTO>) personajeService.getAllPersonajes();
        return ResponseEntity.ok(personajes);
    }
    
    @DeleteMapping("{/id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}