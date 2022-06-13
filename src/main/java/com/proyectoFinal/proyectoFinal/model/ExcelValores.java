package com.proyectoFinal.proyectoFinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "excel_valores_empleado")
public class ExcelValores {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;

    @Getter @Setter
    @Column(name = "sueldo_basico")
    private Double sueldoBasico;

    @Getter @Setter
    @Column(name = "apoyo_soli")
    private Double apoyoSoli;

    @Getter @Setter
    @Column(name = "hora_extra_diur")
    private Double horaExtraDiurno;

    @Getter @Setter
    @Column(name = "hora_extra_fa")
    private Double horaExtraFa;

    @Getter @Setter
    @Column(name = "comisiones")
    private Double comisiones;

    @Getter @Setter
    @Column(name = "vacaciones_d")
    private Double vacacionesD;

    @Getter @Setter
    @Column(name = "vacaciones_o")
    private Double vacacionesO;

    @Getter @Setter
    @Column(name = "aj")
    private Double aj;

    @Getter @Setter
    @Column(name = "bono_retiro")
    private Double bonoRetiro;

    @Getter @Setter
    @Column(name = "compensacion")
    private Double compensacion;

    @Getter @Setter
    @Column(name = "incapacidades")
    private Double incapacidades;

    @Getter @Setter
    @Column(name = "referencia", nullable = false,length = 100)
    private String referencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Double getApoyoSoli() {
        return apoyoSoli;
    }

    public void setApoyoSoli(Double apoyoSoli) {
        this.apoyoSoli = apoyoSoli;
    }

    public Double getHoraExtraDiurno() {
        return horaExtraDiurno;
    }

    public void setHoraExtraDiurno(Double horaExtraDiurno) {
        this.horaExtraDiurno = horaExtraDiurno;
    }

    public Double getHoraExtraFa() {
        return horaExtraFa;
    }

    public void setHoraExtraFa(Double horaExtraFa) {
        this.horaExtraFa = horaExtraFa;
    }

    public Double getComisiones() {
        return comisiones;
    }

    public void setComisiones(Double comisiones) {
        this.comisiones = comisiones;
    }

    public Double getVacacionesD() {
        return vacacionesD;
    }

    public void setVacacionesD(Double vacacionesD) {
        this.vacacionesD = vacacionesD;
    }

    public Double getVacacionesO() {
        return vacacionesO;
    }

    public void setVacacionesO(Double vacacionesO) {
        this.vacacionesO = vacacionesO;
    }

    public Double getAj() {
        return aj;
    }

    public void setAj(Double aj) {
        this.aj = aj;
    }

    public Double getBonoRetiro() {
        return bonoRetiro;
    }

    public void setBonoRetiro(Double bonoRetiro) {
        this.bonoRetiro = bonoRetiro;
    }

    public Double getCompensacion() {
        return compensacion;
    }

    public void setCompensacion(Double compensacion) {
        this.compensacion = compensacion;
    }

    public Double getIncapacidades() {
        return incapacidades;
    }

    public void setIncapacidades(Double incapacidades) {
        this.incapacidades = incapacidades;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public ExcelValores(){}
}
