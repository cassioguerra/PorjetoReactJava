package com.dev.backend.service;

import com.dev.backend.entity.Permissao;
import com.dev.backend.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    PermissaoRepository permisaoRepository;

    public List<Permissao> buscaTodos(){
        return  permisaoRepository.findAll();
    }

    public Permissao inserir (Permissao permissao){
        permissao.setDataCriacao(new Date());
        Permissao novaPermissao = permisaoRepository.saveAndFlush(permissao);
        return novaPermissao;
    }

    public Permissao alterar( Permissao permissao){
        permissao.setDataAtualizacao(new Date());
        return permisaoRepository.saveAndFlush(permissao);
    }

    public void excluir ( Long id){
        Permissao permissao = permisaoRepository.findById(id).get();
        permisaoRepository.delete(permissao);
    }
}
