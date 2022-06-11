package com.proyectoFinal.proyectoFinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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

    @Getter @Setter @Column(name = "estado")
    private String estado;

    public Usuario(){}

    public Usuario(String userName, String nombres, String apellidos, String tipoDocumento, String noDocumento, String sexo, String direccion, String telefono, String rol, String email, String password, String estado) {
        this.userName = userName;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.noDocumento = noDocumento;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.email = email;
        this.password = password;
        this.estado = estado;
    }
}
