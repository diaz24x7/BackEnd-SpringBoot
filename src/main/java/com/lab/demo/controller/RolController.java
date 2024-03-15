package com.lab.demo.controller;

import com.lab.demo.model.Rol;
import com.lab.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RolController {

    @Autowired
    RolRepository RolRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getAllRols(@RequestParam(required = false) String title) {
        try {
            List<Rol> roles = new ArrayList<Rol>();

            if (title == null)
                roles.addAll(RolRepository.findAll());
            else
                roles.addAll(RolRepository.findByEstado(title));

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable("id") long id) {
        Optional<Rol> rolData = RolRepository.findById(id);

        return rolData
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/roles")
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        try {
            Rol _rol = RolRepository
                    .save(new Rol(
                            rol.getIdRol(),
                            rol.getNombre(),
                            rol.getDescripcion(),
                            rol.getEstado(),
                            rol.getFechaCreacion(),
                            rol.getFechaModificacion()
                    ));
            return new ResponseEntity<>(_rol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable("id") long id, @RequestBody Rol rol) {
        Optional<Rol> rolData = RolRepository.findById(id);

        if (rolData.isPresent()) {
            Rol _rol = rolData.get();
            _rol.setIdRol(rol.getIdRol());
            _rol.setNombre(rol.getNombre());
            _rol.setDescripcion(rol.getDescripcion());
            _rol.setEstado(rol.getEstado());
            _rol.setFechaCreacion(rol.getFechaCreacion());
            _rol.setFechaModificacion(rol.getFechaModificacion());
            return new ResponseEntity<>(RolRepository.save(_rol), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<HttpStatus> deleteRol(@PathVariable("id") long id) {
        try {
            RolRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/roles")
    public ResponseEntity<HttpStatus> deleteAllRols() {
        try {
            RolRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/roles/published")
    public ResponseEntity<List<Rol>> findByRol() {
        try {
            List<Rol> roles = RolRepository.findByNombre("Cliente");

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
