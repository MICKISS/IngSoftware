package com.proyectoFinal.proyectoFinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "excel_detalle")
public class ExcelDetalle {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;

    @Getter @Setter
    @Column(name = "orden" , nullable = false)
    private int orden;
    @Getter @Setter
    @Column(name = "tipo_documento" , nullable = false)
    private String tipoDocumento;
    @Getter @Setter
    @Column(name = "no_documento" , nullable = false)
    private String noDocumento;
    @Getter @Setter
    @Column(name = "cotizante" , nullable = false)
    private String cotizante;

    @Getter @Setter
    @Column(name = "cargo" , nullable = false)
    private String cargo;

    @Getter @Setter
    @Column(name = "anio" , nullable = false)
    private String anio;
    @Getter @Setter
    @Column(name = "mes" , nullable = false)
    private String mes;

    @Getter @Setter
    @Column(name = "salario" , nullable = false)
    private String salario;

    @Getter @Setter
    @Column(name = "dias_trabajados" , nullable = false)
    private String diasTrabajados;
    @Getter @Setter
    @Column(name = "dias_incap" , nullable = false)
    private String diasIncap;
    @Getter @Setter
    @Column(name = "dias_licen" , nullable = false)
    private String diasLicen;

    @Getter @Setter
    @Column(name = "total_dias" , nullable = false)
    private String totalDias;

    @Getter @Setter
    @Column(name = "fecha_ingreso" , nullable = false)
    private String fechaIngreso;

    @Getter @Setter
    @Column(name = "referencia", nullable = false,length = 100)
    private String referencia;

    public ExcelDetalle(int id, int orden, String tipoDocumento, String noDocumento, String cotizante, String cargo, String anio, String mes, String salario, String diasTrabajados, String diasIncap, String diasLicen, String totalDias, String fechaIngreso, String referencia) {
        this.id = id;
        this.orden = orden;
        this.tipoDocumento = tipoDocumento;
        this.noDocumento = noDocumento;
        this.cotizante = cotizante;
        this.cargo = cargo;
        this.anio = anio;
        this.mes = mes;
        this.salario = salario;
        this.diasTrabajados = diasTrabajados;
        this.diasIncap = diasIncap;
        this.diasLicen = diasLicen;
        this.totalDias = totalDias;
        this.fechaIngreso = fechaIngreso;
        this.referencia = referencia;
    }

    public ExcelDetalle(){

    }
}
