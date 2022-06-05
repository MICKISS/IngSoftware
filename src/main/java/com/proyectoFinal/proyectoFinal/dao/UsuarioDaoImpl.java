package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

    @Autowired
    private JavaMailSender javaMailSender;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";

        return entityManager.createQuery(query).getResultList();

    }


    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public boolean obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE userName = :userName AND password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("userName", usuario.getUserName())
                .setParameter("password", usuario.getPassword())
                .getResultList();


        return !lista.isEmpty();
//        if (lista.isEmpty()) {
//            return null;
//        }

//        String passwordHashed = lista.get(0).getPassword();
//
//        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//        if (argon2.verify(passwordHashed, usuario.getPassword())) {
//            return lista.get(0);
//        }
//        return null;
    }

    @Override
    public void eliminar(String username) {
        Usuario usuario = entityManager.find(Usuario.class, username);
        entityManager.remove(usuario);

    }

    @Override
    public void modificar(Usuario username) {
        Usuario usuario = entityManager.find(Usuario.class, username);
        entityManager.remove(usuario);
        entityManager.merge(usuario);
    }

    public Usuario findById(String username) {
        Usuario usuario = entityManager.find(Usuario.class, username);
        if (usuario == null) {
            throw new EntityNotFoundException("No se encontro el usuario"
                    + username);
        }
        return usuario;
    }


    public void enviarMail(String from, String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        javaMailSender.send(mail);

    }


}
