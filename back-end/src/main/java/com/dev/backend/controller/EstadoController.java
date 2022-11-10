package com.dev.backend.controller;


import com.dev.backend.entity.Estado;
import com.dev.backend.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    // lista todos
    @GetMapping("/")
    public List<Estado> buscaTodos(){
        return estadoService.buscatodos();
    }
// CRINDO ESTADO #1
    @PostMapping("/")
    public Estado inserir(@RequestBody Estado estado){
        return estadoService.inserir(estado);
    }
// Editar estado #2
    @PutMapping("/")
    public Estado alterar(@RequestBody Estado estado) {
        return  estadoService.alterar(estado);
    }
//delete #3
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable("id") Long id){
        estadoService.excluir(id);
        return ResponseEntity.ok().build();
    }







}
