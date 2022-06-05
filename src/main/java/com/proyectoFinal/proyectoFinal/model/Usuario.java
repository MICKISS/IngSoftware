package com.proyectoFinal.proyectoFinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Getter @Setter @Column(name = "username" , nullable = false)
    private String userName;

    @Getter @Setter
    @Column(name = "nombres")
    private String nombres;

    @Getter @Setter @Column(name = "apellidos")
    private String apellidos;

    @Getter @Setter @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Getter @Setter @Column(name = "no_documento")
    private String noDocumento;

    @Getter @Setter @Column(name = "sexo")
    private String sexo;

    @Getter @Setter @Column(name = "direccion")
    private String direccion;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "rol")
    private String rol;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;


    public Usuario(String username, String userName, String nombres, String apellidos, String tipoDocumento, String noDocumento, String sexo, String direccion, String telefono, String rol) {
    }
    public Usuario(){}
}
