package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
