package com.dev.backend.controller;

import com.dev.backend.entity.Cidade;
import com.dev.backend.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;
    @GetMapping("/")
    public List<Cidade> buscatodos(){
        return cidadeService.buscatodos();
    }
    @PostMapping("/")
    public Cidade Inserir( @RequestBody  Cidade cidade){
        return cidadeService.Inserir(cidade);
    }
    @PutMapping("/")
    public  Cidade alterar(@RequestBody Cidade cidade){
        return cidadeService.alterar(cidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable("id") Long id ){
        cidadeService.excluir(id);
        return ResponseEntity.ok().build();
    }



}
