package com.dev.backend.service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
     PessoaRepository pessoaRepository;

    public List<Pessoa> buscaTodos(){
        return pessoaRepository.findAll();
    }

    public Pessoa Inserir(Pessoa pessoa){
pessoa.setDataCriacao(new Date());
Pessoa pessoaNova = pessoaRepository.saveAndFlush(pessoa);
return pessoaNova;
    }

    public Pessoa alterar(Pessoa pessoa){
        pessoa.setDataAtualizacao(new Date());
        return pessoaRepository.saveAndFlush(pessoa);

    }

    public  void excluir(Long id){
        Pessoa pessoa =pessoaRepository.findById(id).get();
        pessoaRepository.delete(pessoa);
    }


}
