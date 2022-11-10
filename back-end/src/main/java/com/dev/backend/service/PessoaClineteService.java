package com.dev.backend.service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PermissaoRepository;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PessoaClineteService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private  PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private  EmailService emailService;


    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
             pessoa.setDataCriacao(new Date());
             Pessoa objetoNovo = pessoaRepository.saveAndFlush(pessoa);
             permissaoPessoaService.vincularPessoaPermissaoCliente(objetoNovo);
           // emailService.enviarEmmailTexto( objetoNovo.getEmail(), "Cadastro na loja de cassio guerra", " o registro na loja foi realizado com sucesso! Em breve voce recebera a senha de acesso por email");
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("nome", objetoNovo.getNome());
        propMap.put("mensagen", " o registro na loja foi realizado com sucesso! Em breve voce recebera a senha de acesso por email");
        propMap.put("cpf", objetoNovo.getCpf());
        propMap.put("email", objetoNovo.getEmail());
        propMap.put("senha", objetoNovo.getSenha());

        emailService.enviarEmailTenplate(objetoNovo.getEmail(), "Cadastro na loja de cassio guerra", propMap);

             return  objetoNovo;
    }



}
