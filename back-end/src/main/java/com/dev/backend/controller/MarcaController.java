package com.dev.backend.controller;

import com.dev.backend.entity.Marca;
import com.dev.backend.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

@GetMapping("/")
    public List<Marca> buscatodos(){
        return marcaService.buscatodos();
    }
@PostMapping("/")
    public Marca Inserir (@RequestBody Marca objeto){
        return marcaService.Inserir(objeto);
    }
@PutMapping("/")
    public Marca alterar(@RequestBody Marca objeto){
        return marcaService.alterar(objeto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id ){
        marcaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
