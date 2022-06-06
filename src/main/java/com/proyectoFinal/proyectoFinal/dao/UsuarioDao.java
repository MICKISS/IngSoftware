package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.Auditoria;
import com.proyectoFinal.proyectoFinal.model.Usuario;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();



    void registrar(Usuario usuario);



    boolean obtenerUsuarioPorCredenciales(Usuario usuario);


    void eliminar(String username);

    void modificar(Usuario usuario);
     Usuario findById(String username);

    void enviarMail(String from, String to, String subject, String body);

    String getClientIp(HttpServletRequest request);
}
