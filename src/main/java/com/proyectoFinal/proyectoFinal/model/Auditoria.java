package com.proyectoFinal.proyectoFinal.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;

    @Getter @Setter @Column(name = "usuario")

    private String usuario;
    @Getter @Setter @Column(name = "actividad")
    private String actividad;
    @Getter @Setter @Column(name = "fecha")
    private Timestamp fecha;
    @Getter @Setter @Column(name = "ip")
    private String ip;
    public Auditoria(){}
}
