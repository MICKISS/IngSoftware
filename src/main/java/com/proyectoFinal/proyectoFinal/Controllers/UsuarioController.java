package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.Services.EnviarEmailService;
import com.proyectoFinal.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.proyectoFinal.dao.UsuarioRepository;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import com.proyectoFinal.proyectoFinal.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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



//    @RequestMapping(value="api/usuarios/{username}",method= RequestMethod.GET)
//    public Usuario getUsuario(@PathVariable String userName){
//        Usuario usuario = new Usuario();
//        usuario.setId(id);
//        usuario.setNombre("Miguel");
//        usuario.setApellido("S");
//        usuario.setEmail("ma@gmail.com");
//        usuario.setTelefono("123");
//        return usuario;
//    }

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
        System.out.println("ENTRO"+username);
        return usuarioDao.findById(username);

                //new Usuario(username,
//                usuarioRepository.findById(username).get().getUserName(),
//                usuarioRepository.findById(username).get().getNombres(),
//                usuarioRepository.findById(username).get().getApellidos(),
//                usuarioRepository.findById(username).get().getTipoDocumento(),
//                usuarioRepository.findById(username).get().getNoDocumento(),
//                usuarioRepository.findById(username).get().getSexo(),
//                usuarioRepository.findById(username).get().getDireccion(),
//                usuarioRepository.findById(username).get().getTelefono(),
//                usuarioRepository.findById(username).get().getRol());

    }


//    @RequestMapping(value="usuario1")
//    public Usuario editar(){
//        Usuario usuario = new Usuario();
//        usuario.setNombre("Miguel");
//        usuario.setApellido("S");
//        usuario.setEmail("ma@gmail.com");
//        usuario.setTelefono("1231233");
//        return usuario;
//    }



}
