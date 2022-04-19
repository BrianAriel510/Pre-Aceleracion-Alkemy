
package com.alekmy.peliculas.controller;

import com.alekmy.peliculas.dto.GeneroBasicDTO;
import com.alekmy.peliculas.dto.GeneroDTO;
import com.alekmy.peliculas.service.IGeneroService;
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
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private IGeneroService generoService;
    
    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroBasicDTO genero) {
        GeneroDTO generoGuardado = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

   @GetMapping("/all")// ME DEVUELVE SOLO LOS GENEROS (Basic)
   public ResponseEntity<List<GeneroBasicDTO>> getAllBasic(){
       List<GeneroBasicDTO> generosList = generoService.getAllGenerosBasic();
       return ResponseEntity.ok(generosList);
   }
    
   @DeleteMapping("/{id}")//request param
   public ResponseEntity<Void> delete (@PathVariable Long id){
       generoService.delete(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
   
}