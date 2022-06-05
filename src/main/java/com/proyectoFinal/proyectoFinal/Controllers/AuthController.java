package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import com.proyectoFinal.proyectoFinal.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="api/login",method= RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        if(usuarioDao.obtenerUsuarioPorCredenciales(usuario)){
            return "OK";
        }
        return "FAIL";
//        Usuario usuarioLogueado=usuarioDao.obtenerUsuarioPorCredenciales(usuario);
//        if(usuarioLogueado!=null){
//            String tokenJwt = jwtUtil.create(usuarioLogueado.getUserName(),usuarioLogueado.getUserName());
//            return tokenJwt;
//        }
//        return "FAIL";
    }
}
