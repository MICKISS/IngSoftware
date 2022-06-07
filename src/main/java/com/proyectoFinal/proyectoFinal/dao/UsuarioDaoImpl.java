package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    @Autowired
    private JavaMailSender javaMailSender;

    UsuarioRepository usuarioRepository;


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
//    public boolean validarUsername(String username) {
//        Usuario usuario = entityManager.find(Usuario.class, username);
//        boolean encontrado = false;
//        if (usuario != null) {
//            encontrado= true;
//        }
//        return encontrado;
//    }




    public void enviarMail(String from, String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        javaMailSender.send(mail);

    }

    @Override
    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }



}
