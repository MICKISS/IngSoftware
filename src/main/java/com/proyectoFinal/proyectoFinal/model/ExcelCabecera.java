package com.proyectoFinal.proyectoFinal.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "excel_cabecera")
public class ExcelCabecera {
    @Id
    @Getter @Setter
    @Column(name = "referencia", nullable = false,length = 100)
    private String referencia;

    @Getter @Setter
    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Getter @Setter
    @Column(name = "no_documento", nullable = false)
    private String noDocumento;


    @Getter @Setter
    @Column(name = "razon_social", nullable = false)
    private String razonSocial;



    @Getter @Setter
    @Column(name = "solicitud", nullable = false)
    private String solicitud;

    public ExcelCabecera(String referencia, String tipoDocumento, String noDocumento, String razonSocial, String solicitud) {
        this.referencia = referencia;
        this.tipoDocumento = tipoDocumento;
        this.noDocumento = noDocumento;
        this.razonSocial = razonSocial;
        this.solicitud = solicitud;
    }

    public ExcelCabecera(){}
}
