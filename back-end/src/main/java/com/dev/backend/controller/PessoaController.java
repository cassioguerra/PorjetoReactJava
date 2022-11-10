package com.dev.backend.controller;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    public PessoaService pessoaService;
@GetMapping("/")
    public List<Pessoa> buscaTodos(){
        return pessoaService.buscaTodos();
    }
@PostMapping("/")
    public Pessoa inserir( @RequestBody Pessoa pessoa){
        return pessoaService.Inserir(pessoa);
    }

@PutMapping("/")
    public Pessoa alterar( @RequestBody Pessoa pessoa){
        return pessoaService.alterar(pessoa);
    }
@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        pessoaService.excluir(id);
        return ResponseEntity.ok().build();

    }
}
