package com.lab.demo.controller;

import com.lab.demo.model.Usuario;
import com.lab.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios(@RequestParam(required = false) String estado) {
        try {
            List<Usuario> usuarios = new ArrayList<Usuario>();

            if (estado == null)
                usuarios.addAll(usuarioRepository.findAll());
            else
                usuarios.addAll(usuarioRepository.findByEstado(estado));

            if (usuarios.isEmpty()) {
                return new ResponseEntity<>("hola",HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long id) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        return usuarioData
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario _usuario = usuarioRepository
                    .save(new Usuario(
                            usuario.getIdRol(),
                            usuario.getNombre(),
                            usuario.getApellido(),
                            usuario.getTipoDocumento(),
                            usuario.getIdentificacion(),
                            usuario.getEmail(),
                            usuario.getPassw(),
                            usuario.getEstado(),
                            usuario.getFechaCreacion(),
                            usuario.getFechaModificacion()
                    ));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        if (usuarioData.isPresent()) {
            Usuario _usuario = usuarioData.get();
            _usuario.setIdUsuario(usuario.getIdUsuario());
            _usuario.setIdRol(usuario.getIdRol());
            _usuario.setNombre(usuario.getNombre());
            _usuario.setApellido(usuario.getApellido());
            _usuario.setTipoDocumento(usuario.getTipoDocumento());
            _usuario.setIdentificacion(usuario.getIdentificacion());
            _usuario.setEmail(usuario.getEmail());
            _usuario.setPassw(usuario.getPassw());
            _usuario.setEstado(usuario.getEstado());
            _usuario.setFechaCreacion(usuario.getFechaCreacion());
            _usuario.setFechaModificacion(usuario.getFechaModificacion());
            return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<HttpStatus> deleteAllUsuarios() {
        try {
            usuarioRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/usuarios/published")
    public ResponseEntity<List<Usuario>> findByRol() {
        try {
            List<Usuario> usuarios = usuarioRepository.findByIdRol(0);

            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
