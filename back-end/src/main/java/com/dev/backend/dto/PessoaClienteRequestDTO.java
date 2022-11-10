package com.dev.backend.dto;

import com.dev.backend.entity.Cidade;
import com.dev.backend.entity.Pessoa;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PessoaClienteRequestDTO {

    // RESPONSAVEL POR TRTANFERENCIA DE DADOS

    private  String nome;
    private  String cpf;
    private  String email;
    private  String endereco;
    private  String cep;
    private Cidade cidade;

    // PARA CONVERTE A PESSOA DTO PARA PESSOA
public Pessoa converter(PessoaClienteRequestDTO pessoaClienteRequestDTO){
    Pessoa pessoa = new Pessoa();
    // metodo de converção  de pessoaDTO para pessoaEntidade
    BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoa );
    return pessoa;
}

}
