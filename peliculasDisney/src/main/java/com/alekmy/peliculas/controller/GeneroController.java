
package com.alekmy.peliculas.controller;

import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.service.IGeneroService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private IGeneroService generoService;
    
    @PostMapping
    public ResponseEntity<GeneroDTO> save(@Valid @RequestBody GeneroDTO genero) {
        GeneroDTO generoGuardado = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }
    
}