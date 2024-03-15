package com.lab.demo.controller;

import com.lab.demo.dto.ReservaDTO;
import com.lab.demo.dto.ReservaResponse;
import com.lab.demo.model.Reserva;
import com.lab.demo.model.Rol;
import com.lab.demo.model.Usuario;
import com.lab.demo.repository.ReservaRepository;
import com.lab.demo.repository.RolRepository;
import com.lab.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservaController {

    @Autowired
    ReservaRepository ReservaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;

    @GetMapping("/reservas")
    public ResponseEntity<ReservaResponse> getAllReservas(@RequestParam(required = false) String title) {
        try {

            List<ReservaDTO> reservaDTOS= new ArrayList<>();
            List<Reserva> reservas = new ArrayList<>();

            if (title == null) {
                reservas.addAll(ReservaRepository.findAll());
                reservas.forEach(reserva -> {
                    Optional<Usuario> usuarioData = usuarioRepository.findById(Long.valueOf(reserva.getIdUsuario()));
                    Optional<Rol> rolData = rolRepository.findById(Long.valueOf(usuarioData.get().getIdRol()));
                    reservaDTOS.add(new ReservaDTO(reserva,usuarioData.get(),rolData.get()));
                });
            }
            else
                reservas.addAll(ReservaRepository.findByEstado(title));

            if (reservas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ReservaResponse reservaResponses = new ReservaResponse(reservaDTOS,"Consulta Exitosa");
            return new ResponseEntity<>(reservaResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gestion_reserva")
    public ResponseEntity<?>getGestiónReserva (){
        return ResponseEntity.ok(ReservaRepository.getGestiónReserva());
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable("id") long id) {
        Optional<Reserva> reservaData = ReservaRepository.findById(id);

        return reservaData
                .map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/reservas")
    public ResponseEntity<Reserva> createReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            System.out.println("Ok");
            Usuario _usuario = usuarioRepository.save(new Usuario(
                    1,
                    reservaDTO.getUsuario().getNombre(),
                    reservaDTO.getUsuario().getApellido(),
                    reservaDTO.getUsuario().getTipoDocumento(),
                    reservaDTO.getUsuario().getIdentificacion(),
                    reservaDTO.getUsuario().getEmail(),
                    "Generico123",
                    "ACTIVO",
                    Timestamp.valueOf(LocalDateTime.now()),
                    Timestamp.valueOf(LocalDateTime.now())
            ));
            Reserva _reserva = ReservaRepository.save(new Reserva(
                    _usuario.getIdUsuario(),
                    reservaDTO.getReserva().getFechaReserva(),
                    reservaDTO.getReserva().getTipoReserva(),
                    reservaDTO.getReserva().getCantidadPersonas(),
                    reservaDTO.getReserva().getObservaciones(),
                    "ACTIVO",
                    Timestamp.valueOf(LocalDateTime.now()),
                    Timestamp.valueOf(LocalDateTime.now())
                    ));
            return new ResponseEntity<>(_reserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reservas/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable("id") long id, @RequestBody Reserva reserva) {
        Optional<Reserva> reservaData = ReservaRepository.findById(id);

        if (reservaData.isPresent()) {
            Reserva _reserva = reservaData.get();
            _reserva.setFechaReserva(reserva.getFechaReserva());
            _reserva.setTipoReserva(reserva.getTipoReserva());
            _reserva.setCantidadPersonas(reserva.getCantidadPersonas());
            _reserva.setObservaciones(reserva.getObservaciones());
            _reserva.setFechaModificacion(Timestamp.valueOf(LocalDateTime.now()));

            return new ResponseEntity<>(ReservaRepository.save(_reserva), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reservas/confirmado/{id}")
    public ResponseEntity<Reserva> updateConfirmarReserva(@PathVariable("id") long id) {
        Optional<Reserva> reservaData = ReservaRepository.findById(id);

        if (reservaData.isPresent()) {
            Reserva _reserva = reservaData.get();
            _reserva.setEstado("CONFIRMADO");

            return new ResponseEntity<>(ReservaRepository.save(_reserva), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<HttpStatus> deleteReserva(@PathVariable("id") long id) {
        try {
            ReservaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/reservas")
    public ResponseEntity<HttpStatus> deleteAllReservas() {
        try {
            ReservaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/reservas/published")
    public ResponseEntity<List<Reserva>> findByRol() {
        try {
            List<Reserva> reservas = ReservaRepository.findByIdUsuario(0);

            if (reservas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
