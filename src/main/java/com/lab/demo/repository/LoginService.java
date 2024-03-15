package com.lab.demo.repository;

import com.lab.demo.dto.LoginRequest;
import com.lab.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginService  extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
