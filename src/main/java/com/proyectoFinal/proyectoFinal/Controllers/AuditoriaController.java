package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.dao.AuditoriaDao;
import com.proyectoFinal.proyectoFinal.model.Auditoria;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
public class AuditoriaController {
    static String ip ="";
    @Autowired
    private AuditoriaDao auditoriaDao;

    @RequestMapping(value = "api/auditoria", method = RequestMethod.GET)
    public List<Auditoria> getHistorial() {

        return auditoriaDao.getHistorial();
    }



    @RequestMapping(value = "api/auditoria", method = RequestMethod.POST)
    public void registrarAuditoria(@RequestBody Auditoria auditoria) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        auditoria.setIp(ip);
        Timestamp timestamp = Timestamp.valueOf(timeStamp);
        auditoria.setFecha(timestamp);


        auditoriaDao.registrar(auditoria);
    }
}
