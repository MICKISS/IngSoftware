package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.Auditoria;
import com.proyectoFinal.proyectoFinal.model.Usuario;


import java.util.List;

public interface AuditoriaDao {
    List<Auditoria> getHistorial();
    void registrar(Auditoria auditoria);
}
