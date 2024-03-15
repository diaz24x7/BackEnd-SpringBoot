package com.lab.demo.repository;

import com.lab.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByIdRol(Integer id_rol);
    List<Usuario> findByEstado(String estado);
}
