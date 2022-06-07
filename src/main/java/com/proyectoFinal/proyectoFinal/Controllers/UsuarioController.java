package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.Services.EnviarEmailService;
import com.proyectoFinal.proyectoFinal.dao.AuditoriaDao;
import com.proyectoFinal.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.proyectoFinal.dao.UsuarioRepository;
import com.proyectoFinal.proyectoFinal.model.Auditoria;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import com.proyectoFinal.proyectoFinal.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    public void registrarUsuario(@RequestBody Usuario usuario) {
        //Encripta la contraseña
//        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//        String hash=argon2.hash(1,1024,1,usuario.getPassword());
//
//        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }


    @RequestMapping(value = "api/usuarios/{username}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable String username) {
        usuarioDao.eliminar(username);
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
//    @RequestMapping(value="api/validarusuario/{username}", method = RequestMethod.GET)
//    private boolean verificarUsuario(@PathVariable("username") String username) {
//
//        if(usuarioDao.validarUsername(username)== true){
//            return true;
//        }
//        return false;
//
//    }

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



}
