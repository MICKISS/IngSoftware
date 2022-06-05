package com.proyectoFinal.proyectoFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EnviarEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarMail(String from, String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("miguelsimor@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        javaMailSender.send(mail);
        System.out.println("MENSAJE ENVIADO");
    }
}
