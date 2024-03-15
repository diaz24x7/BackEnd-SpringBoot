package com.lab.demo.dto;

import com.lab.demo.model.Usuario;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private Usuario usuario;
    private String mensaje;

    public LoginResponse() {
    }

    public LoginResponse(Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
