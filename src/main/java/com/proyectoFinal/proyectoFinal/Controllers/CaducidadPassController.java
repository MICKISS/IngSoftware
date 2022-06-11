package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.dao.CaducidadPassDao;

import com.proyectoFinal.proyectoFinal.model.Auditoria;
import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CaducidadPassController {
    @Autowired
    private CaducidadPassDao caducidad;

//    @RequestMapping(value = "api/caducidad", method = RequestMethod.GET)
//    public List<CaducidadPassword> getHistorial() {
//
//        return caducidad.getCaducidad();
//    }

    @RequestMapping(value = "api/caducidad", method = RequestMethod.POST)
    public void registrarCaducidad(@RequestBody CaducidadPassword caducidadPass) {

        int dia = caducidadPass.getFechaFinal().getDate();
        int dias = dia+ caducidadPass.getDias();
        caducidadPass.getFechaFinal().setDate(dias);
        caducidadPass.setFechaFinal(caducidadPass.getFechaFinal());
        caducidad.registrar(caducidadPass);
    }


    @RequestMapping(value = "api/cambiarcaducidad/{username}", method = RequestMethod.PUT)
    public int cambiarCaducidad(@PathVariable String username) {
        return caducidad.modificarCaducidad(username);
    }



}
