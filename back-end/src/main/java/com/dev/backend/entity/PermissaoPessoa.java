package com.dev.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "permissaoPessoa")
@Data
public class PermissaoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // relação de muitos para muitos
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    // ANOTAÇÃO PARA IGNORA O OBJERO PESSOA NO JASAPM
    @JsonIgnore
    private  Pessoa pessoa;
    @ManyToOne
    @JoinColumn( name = "idPermissao")
    private  Permissao permissao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
