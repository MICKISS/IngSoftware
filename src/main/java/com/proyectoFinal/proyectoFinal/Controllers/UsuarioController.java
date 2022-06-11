package com.proyectoFinal.proyectoFinal.Controllers;


import com.proyectoFinal.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.proyectoFinal.dao.UsuarioRepository;

import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import com.proyectoFinal.proyectoFinal.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;
import java.security.*;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios() {

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);

        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) throws NoSuchAlgorithmException {

        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(usuario.getPassword().getBytes(),0,usuario.getPassword().length());

        String md5 = new BigInteger(1,m.digest()).toString(16);


//
       usuario.setPassword(md5);
       usuario.setEstado("Activo");

        usuarioDao.registrar(usuario);
    }


    @RequestMapping(value = "api/usuarios/{username}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable String username)
    {
        if (usuarioDao.getUsuarios().size() != 0) {
            for (int i = 0; i < usuarioDao.getUsuarios().size(); i++) {
                if (usuarioDao.getUsuarios().get(i).getUserName().equals(username)
                ) {
                    usuarioDao.getUsuarios().get(i).setEstado("Inactivo");
                }
            }
        }

    }

    @RequestMapping(value = "api/sendmail/{username}/{password}/{nombres}/{mail}", method = RequestMethod.POST)
    public String sendMail(@PathVariable  String username, @PathVariable String password, @PathVariable String nombres, @PathVariable  String mail){


        String message = "Hola " +nombres+" las credenciales de acceso son:\n" + "\nUsername: " + username + "\nContraseña: " + password+ "\nURL: " + "http://localhost:8080/login.html";
        usuarioDao.enviarMail("admnistradores1@gmail.com",mail,"Credenciales de acceso",message);

        return "usuarios";
    }


    @RequestMapping(value="api/usuarios/{username}", method = RequestMethod.PUT)
    private Usuario modificarUsuario(@PathVariable("username") String username) {

        return usuarioDao.findById(username);

    }


    @GetMapping("api/validarusuario/{username}")
    private String validateName(@PathVariable("username") String userName) {
        String result = "false";

        if (usuarioDao.getUsuarios().size() != 0) {
            for (int i = 0; i < usuarioDao.getUsuarios().size(); i++) {
                if (usuarioDao.getUsuarios().get(i).getUserName().equals(userName)
                ) {
                    result = "true";
                    break;
                }
            }
        }
        return result;
    }




    @RequestMapping(value ="api/ip", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        String clientIp = usuarioDao.getClientIp(request);
        AuditoriaController.ip=clientIp;

        return clientIp;
    }
    @RequestMapping(value = "api/ca", method = RequestMethod.POST)
    public void registrarCaducidad(@RequestBody CaducidadPassword caducidadPass) {
        System.out.println("ENTROCONTROLLER");
        usuarioDao.registrarC(caducidadPass);
    }


    @RequestMapping(value = "api/cambiapass/{username}/{password}", method = RequestMethod.POST)
    public void cambiaPass(@PathVariable String username,@PathVariable String password) throws NoSuchAlgorithmException {

        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(password.getBytes(),0,password.length());

        String md5 = new BigInteger(1,m.digest()).toString(16);
        usuarioDao.modificar(username,md5);
    }


}
