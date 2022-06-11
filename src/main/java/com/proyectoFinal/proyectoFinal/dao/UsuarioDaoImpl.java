package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.io.*;

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
    public boolean obtenerUsuarioPorCredenciales(Usuario usuario) throws IOException, NoSuchAlgorithmException {
        String query = "FROM Usuario WHERE userName = :userName AND password = :password";
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(usuario.getPassword().getBytes(), 0, usuario.getPassword().length());

        String md5 = new BigInteger(1, m.digest()).toString(16);


        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("userName", usuario.getUserName())
                .setParameter("password", md5)
                .getResultList();


        return !lista.isEmpty();

    }


    @Override
    public void modificar(String username,String contraseña) {

        Usuario usuario = entityManager.find(Usuario.class, username);

        String userName = usuario.getUserName();
        String nombres = usuario.getNombres();
        String apellidos = usuario.getApellidos();
        String tipoDocumento = usuario.getTipoDocumento();
        String noDocumento = usuario.getNoDocumento();
        String sexo = usuario.getSexo();
        String direccion = usuario.getDireccion();
        String telefono = usuario.getTelefono();
        String rol = usuario.getRol();
        String email = usuario.getEmail();
        String password = contraseña;
        String estado = usuario.getEstado();

        entityManager.remove(usuario);

        Usuario nuevoUsuario = new Usuario(userName, nombres, apellidos, tipoDocumento, noDocumento, sexo, direccion, telefono, rol, email, password, estado);
        entityManager.merge(nuevoUsuario);
    }

    @Override
    public void modificarCaducidad(CaducidadPassword username) {
        CaducidadPassword caducidad = entityManager.find(CaducidadPassword.class, username);
        entityManager.remove(caducidad);
        entityManager.merge(caducidad);
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

    @Override
    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }

    @Override
    public void registrarC(CaducidadPassword caducidad) {
        entityManager.merge(caducidad);
    }

//    @Override
//    public void modificarCaducidad(Usuario username) {
//        Usuario usuario = entityManager.find(Usuario.class, username);
//
//
//        entityManager.remove(usuario);
//        entityManager.merge(usuario);
//    }
}
