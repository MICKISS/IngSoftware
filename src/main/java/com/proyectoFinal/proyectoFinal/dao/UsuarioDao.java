package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;
import com.proyectoFinal.proyectoFinal.model.Usuario;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();



    void registrar(Usuario usuario);



    boolean obtenerUsuarioPorCredenciales(Usuario usuario) throws IOException, NoSuchAlgorithmException;



    void modificar(String username,String contraseña);

    void modificarCaducidad(CaducidadPassword username);

    Usuario findById(String username);

    void enviarMail(String from, String to, String subject, String body);

    String getClientIp(HttpServletRequest request);


    void registrarC(CaducidadPassword caducidadPass);
}
