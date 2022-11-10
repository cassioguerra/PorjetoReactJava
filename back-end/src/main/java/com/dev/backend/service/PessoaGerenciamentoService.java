package com.dev.backend.service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PessoaGerenciamentoService {


    @Autowired
   private PessoaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    // enviar codigo de recupera senha
    public String solicitarCodigo(String email) {
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        pessoa.setCodigoRecuperacaoDeSenha(getCodigoRecuperacaoDeSenha(pessoa.getId()));
        pessoa.setDataEnvioCodigo(new Date());
        pessoaRepository.saveAndFlush(pessoa);
        emailService.enviarEmmailTexto(pessoa.getEmail(),"codigo de recuperação de senha","Ola o seu codigo de recuperação de senha é:" +pessoa.getCodigoRecuperacaoDeSenha());
      return "codigo enviado";
    }

    // regra de negocia para recupera senha

    public  String alterarSenha(Pessoa pessoa){
        Pessoa pessoaBanco = pessoaRepository.findByEmailAndCodigoRecuperacaoDeSenha(pessoa.getEmail(), pessoa.getCodigoRecuperacaoDeSenha() );
        if (pessoaBanco!=null) {
            Date deferenca = new Date(new Date().getTime() - pessoaBanco.getDataEnvioCodigo().getTime());

            // fazer alteração dentro de 15 minutos apois a chegada do codigo por emila
            if (deferenca.getTime() / 1000 < 900) {
                // depois usando o spring securite para criptografa a senha
                pessoaBanco.setSenha(pessoa.getSenha());
                pessoaBanco.setCodigoRecuperacaoDeSenha(null);
                pessoaRepository.saveAndFlush(pessoaBanco);
                return "senha alterada com suvesso!!";

            } else {
                return "tempo expirado solicite um novo codigo ";
            }
        }else {
            return"email ou codigo não encontrado";
        }
      }



    private String getCodigoRecuperacaoDeSenha(Long id){
        DateFormat format= new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return  format.format(new Date())+id;
    }



}
