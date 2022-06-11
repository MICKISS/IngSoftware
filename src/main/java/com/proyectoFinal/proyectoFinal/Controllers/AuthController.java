package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.dao.CaducidadPassDao;
import com.proyectoFinal.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import com.proyectoFinal.proyectoFinal.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private CaducidadPassDao caducidadPassDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) throws IOException, NoSuchAlgorithmException {

        if (usuarioDao.obtenerUsuarioPorCredenciales(usuario)) {
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

    @GetMapping("api/rol/{username}")
    private String validateName(@PathVariable("username") String userName) {
        String result = "";

        if (usuarioDao.getUsuarios().size() != 0) {
            for (int i = 0; i < usuarioDao.getUsuarios().size(); i++) {
                if (usuarioDao.getUsuarios().get(i).getUserName().equals(userName)
                ) {
                    result = usuarioDao.getUsuarios().get(i).getRol();
                }
            }
        }

        return result;
    }

    @GetMapping("api/bloquear/{username}")
    private void bloquear(@PathVariable("username") String userName) {

        if (usuarioDao.getUsuarios().size() != 0) {
            for (int i = 0; i < usuarioDao.getUsuarios().size(); i++) {
                if (usuarioDao.getUsuarios().get(i).getUserName().equals(userName)
                ) {
                    usuarioDao.getUsuarios().get(i).setEstado("Bloqueado");
                }
            }
        }
    }

    @GetMapping("api/validarestado/{username}")
    private String validarEstado(@PathVariable("username") String userName) {
        String result = "";

        if (usuarioDao.getUsuarios().size() != 0) {
            for (int i = 0; i < usuarioDao.getUsuarios().size(); i++) {
                if (usuarioDao.getUsuarios().get(i).getUserName().equals(userName)
                ) {
                    if (usuarioDao.getUsuarios().get(i).getEstado().equals("Activo")) {
                        result = "Activo";
                    } else if (usuarioDao.getUsuarios().get(i).getEstado().equals("Bloqueado")) {
                        result = "Bloqueado";
                    } else if (usuarioDao.getUsuarios().get(i).getEstado().equals("Inactivo")) {
                        result = "Inactivo";
                    }
                }
            }
        }

        return result;
    }

    @GetMapping("api/validarcaducidad/{username}")
    private String validarFCaducidad(@PathVariable("username") String userName) {
        String result = "";
        LocalDate todaysDate = LocalDate.now();
        Date fecha = Date.valueOf(todaysDate);


        if (caducidadPassDao.getCaducidad().size() != 0) {
            for (int i = 0; i < caducidadPassDao.getCaducidad().size(); i++) {
                if (caducidadPassDao.getCaducidad().get(i).getUserName().equals(userName)
                ) {
                    int verificar = fecha.compareTo(caducidadPassDao.getCaducidad().get(i).getFechaFinal());
                    if (verificar==0|| verificar>0) {
                        result = "cadujo";
                    } else {

                        result = "disponible";

                    }

                }
            }
        }

        return result;
    }

//    @PutMapping("api/cambiarCaducidad/{username}/{password}")
//    private void cambiarCaducidad(@PathVariable String username, @PathVariable() String password) {
//
//        System.out.println("ENTROOOOOOOO");
//        LocalDate todaysDate = LocalDate.now();
//        Date fecha_inicial = Date.valueOf(todaysDate);
//        Date fecha_final = Date.valueOf(todaysDate);
//        int dias = 0;
//        int dia = 0;
//        int diass = 0;
//
//        if (usuarioDao.getUsuarios().size() != 0) {
//
//            for (int i = 0; i < usuarioDao.getUsuarios().size(); i++) {
//                if (usuarioDao.getUsuarios().get(i).getUserName().equals(username)
//                ) {
//                     dias = usuarioDao.getUsuarios().get(i).getDias();
//                     dia = fecha_inicial.getDate();
//                     diass = dia + dias;
//                    fecha_final.setDate(diass);
//                    usuarioDao.getUsuarios().get(i).setFechaInicial(fecha_inicial);
//                    usuarioDao.getUsuarios().get(i).setFechaFinal(fecha_final);
//                    usuarioDao.getUsuarios().get(i).setPassword(password);
//                }
//            }
//
//        }
//    }

}

