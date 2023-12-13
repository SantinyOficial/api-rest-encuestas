package com.api.rest.encuestas.controller;

import com.api.rest.encuestas.model.Encuesta;
import com.api.rest.encuestas.reposotories.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
public class EncuestaController {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @GetMapping("/encuestas")
    public ResponseEntity<?> listarTodasLasEncuestas() {
        Iterable<Encuesta> encuestas = encuestaRepository.findAll();
        if (((Collection<?>) encuestas).isEmpty()) {
            return new ResponseEntity<>("No hay encuestas disponibles", HttpStatus.NO_CONTENT); // Devolver 204 No Content
        }
        return new ResponseEntity<>(encuestas, HttpStatus.OK);
    }

    @PostMapping("/encuestas")
    public ResponseEntity<?> crearEncuesta(@RequestBody Encuesta encuesta) {
        encuesta = encuestaRepository.save(encuesta);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI newEncuestaUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(encuesta.getId()).toUri();
        httpHeaders.setLocation(newEncuestaUri);

        return new ResponseEntity<>(null, httpHeaders,HttpStatus.CREATED);
    }

    @GetMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> obtenerEncuesta(@PathVariable Long encuestaId){
        Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);

        if (encuesta.isPresent()){
            return new ResponseEntity(encuesta, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> actualizarEncuesta(@RequestBody Encuesta encuesta, @PathVariable Long encuestaId){
        encuesta.setId(encuestaId);
        encuestaRepository.save(encuesta);
        return new ResponseEntity<>("Encuesta actualizada",HttpStatus.OK);
    }

    @DeleteMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId){
        encuestaRepository.deleteById(encuestaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
