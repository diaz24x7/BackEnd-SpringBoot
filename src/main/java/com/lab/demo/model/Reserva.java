package com.lab.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "reserva",  schema = "public")
public class Reserva {

    @Id
    @Column(name = "id_reserva")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idReserva;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "fecha_reserva")
    private Timestamp fechaReserva;

    @Column(name = "tipo_reserva")
    private String tipoReserva;

    @Column(name = "cantidad_personas")
    private Integer cantidadPersonas;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;

    public Reserva() {

    }

    public Reserva(Integer idUsuario, Timestamp fechaReserva, String tipoReserva, Integer cantidadPersonas, String observaciones, String estado, Timestamp fechaCreacion, Timestamp fechaModificacion) {
        this.idUsuario = idUsuario;
        this.fechaReserva = fechaReserva;
        this.tipoReserva = tipoReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.observaciones = observaciones;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "Usuario [ "
                + "  id_reserva = " + this.idReserva
                + ", id_usuario = " + this.idUsuario
                + ", fecha_reserva = " + this.fechaReserva
                + ", tipo_reserva = " + this.tipoReserva
                + ", cantidad_personas = " + this.cantidadPersonas
                + ", observaciones = " + this.observaciones
                + ", estado = " + this.estado
                + ", fecha_creacion = " + this.fechaCreacion
                + ", fecha_modificacion = " + this.fechaModificacion
                + " ] ";
    }

}
