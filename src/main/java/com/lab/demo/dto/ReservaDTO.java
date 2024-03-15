package com.lab.demo.dto;

import com.lab.demo.model.Reserva;
import com.lab.demo.model.Rol;
import com.lab.demo.model.Usuario;

public class ReservaDTO {

    private Reserva reserva;
    private Usuario usuario;
    private Rol rol;

    public ReservaDTO() {
    }

    public ReservaDTO(Reserva reserva, Usuario usuario, Rol rol) {
        this.reserva = reserva;
        this.usuario = usuario;
        this.rol = rol;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
