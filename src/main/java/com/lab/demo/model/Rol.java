package com.lab.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rol",  schema = "public")
public class Rol {

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idRol;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "descripcion")
    String descripcion;

    @Column(name = "estado")
    String estado;

    @Column(name = "fecha_creacion")
    Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion")
    Timestamp fechaModificacion;

    public Rol() {

    }

    public Rol(Integer idRol, String nombre, String descripcion, String estado, Timestamp fechaCreacion, Timestamp fechaModificacion) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
                + "  idRol = " + this.idRol
                + ", nombre = " + this.nombre
                + ", descripcion = " + this.descripcion
                + ", estado = " + this.estado
                + ", fecha_creacion = " + this.fechaCreacion
                + ", fecha_modificacion = " + this.fechaModificacion
                + "  ] ";
    }

}
