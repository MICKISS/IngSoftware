package com.proyectoFinal.proyectoFinal.dao;



import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;

import java.util.List;

public interface CaducidadPassDao {
    List<CaducidadPassword> getCaducidad();
    void registrar(CaducidadPassword caducidad);

    int modificarCaducidad(String username);
}
