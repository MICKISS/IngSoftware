package com.proyectoFinal.proyectoFinal.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "caducidad_password")
public class CaducidadPassword {

    @Id
    @Getter
    @Setter
    @Column(name = "username" , nullable = false)
    private String userName;

    @Getter @Setter
    @Column(name = "fecha_inicial")
    private Date fechaInicial;

    @Getter @Setter
    @Column(name = "fecha_final")
    private Date fechaFinal;

    @Getter @Setter
    @Column(name = "dias")
    private int dias;

    public CaducidadPassword() {

    }
}
