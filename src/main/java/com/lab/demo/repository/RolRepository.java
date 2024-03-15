package com.lab.demo.repository;

import com.lab.demo.model.Rol;
import com.lab.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {
    List<Rol> findByNombre(String nombre);
    List<Rol> findByEstado(String estado);
}
