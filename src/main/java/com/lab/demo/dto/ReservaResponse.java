package com.lab.demo.dto;

import com.lab.demo.model.Reserva;

import java.util.List;

public class ReservaResponse {
    private List<ReservaDTO> reservaDTOS;
    private String mensaje;

    public ReservaResponse() {
    }

    public ReservaResponse(List<ReservaDTO> reservaDTOS, String mensaje) {
        this.reservaDTOS = reservaDTOS;
        this.mensaje = mensaje;
    }

    public List<ReservaDTO> getReservaDTOS() {
        return reservaDTOS;
    }

    public void setReservaDTOS(List<ReservaDTO> reservaDTOS) {
        this.reservaDTOS = reservaDTOS;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
