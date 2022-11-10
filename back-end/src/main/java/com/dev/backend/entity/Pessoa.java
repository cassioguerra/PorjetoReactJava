package com.dev.backend.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nome;
    private String cpf;
    private String email;
    private String codigoRecuperacaoDeSenha;
    @Temporal(TemporalType.TIMESTAMP)
    private  Date dataEnvioCodigo;
    private String senha;
    private String endereco;
    private String cep;
    @ManyToOne
    @JoinColumn(name="idCidade")
    private Cidade cidade;

    @OneToMany(mappedBy ="pessoa", orphanRemoval = true, cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    // esse set sou eu que vou defirni de forma personalisada
    @Setter(value = AccessLevel.NONE)
    private List<PermissaoPessoa>  permissaoPessoas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    // personalizando ao meu set
    // ele tra uma lista de pessoas ja setada
    public void  setPermissaoPessoas(List<PermissaoPessoa> pp){
        for(PermissaoPessoa p:pp){
            p.setPessoa(this);
        }
        this.permissaoPessoas = pp;
    }

}
