package com.dev.backend.controller;

import com.dev.backend.entity.Permissao;
import com.dev.backend.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissao")
public class PermissaoController {

    @Autowired
    PermissaoService permissaoService;

    @GetMapping("/")
    public List<Permissao> buscarTodos(){
        return permissaoService.buscaTodos();
    }

@PostMapping("/")
    public Permissao inseir( @RequestBody Permissao permissao){
        return permissaoService.inserir(permissao);

    }
    @PutMapping("/")
    public Permissao alatear (@RequestBody Permissao permissao){
        return permissaoService.alterar(permissao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir( @PathVariable("id") Long id){
        permissaoService.excluir(id);
        return ResponseEntity.ok().build();

    }
}
