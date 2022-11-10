package com.dev.backend.service;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration fmconfiguration;

    @Value("${spring.mail.username}")
    private String remetente;

    // metodo de envio de email com itexto sinples

    public String enviarEmmailTexto( String destinatario, String titulo,String mensagens){
try {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(remetente);
    simpleMailMessage.setTo(destinatario);
    simpleMailMessage.setSubject(titulo);
    simpleMailMessage.setText(mensagens);
    javaMailSender.send(simpleMailMessage);
    return "Email enciado";
} catch (Exception ex){
return  "ERRO AO ENVIAR EMAIL";

}
    }
    // PARA MANDA EMAIL PESONALIZADO

    public  void enviarEmailTenplate( String destinatario, String titulo, Map<String, Object> propiedades){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(titulo);
            mimeMessageHelper.setFrom(remetente);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setText(geContetFromTemplace(propiedades), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }
    public String geContetFromTemplace(Map <String, Object> model){
        StringBuffer content = new StringBuffer();

        try{
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmconfiguration.getTemplate("RecuperacaoSenha.flth"), model));
        }catch (Exception e){
            e.printStackTrace();
        }
        return  content.toString();

    }
}
