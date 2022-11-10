package com.dev.backend.service;

import com.dev.backend.entity.Categoria;
import com.dev.backend.entity.Marca;
import com.dev.backend.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository   marcaRepository;


    public List<Marca> buscatodos(){
        return marcaRepository.findAll();
    }

    public Marca Inserir(Marca objeto){
        objeto.setDataCriacao(new Date());
        Marca objetoNovo = marcaRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Marca alterar ( Marca objeto){
        objeto.setDataAtualizao(new Date());
        return marcaRepository.saveAndFlush(objeto);
    }

    public  void excluir( Long id){
        Marca objeto = marcaRepository.findById(id).get();
        marcaRepository.delete(objeto);

    }

}
