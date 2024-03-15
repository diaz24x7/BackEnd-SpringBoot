package com.lab.demo.controller;

import com.lab.demo.dto.LoginRequest;
import com.lab.demo.dto.LoginResponse;
import com.lab.demo.model.Usuario;
import com.lab.demo.repository.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            LoginResponse loginResponse = new LoginResponse();
            Optional<Usuario> usuarios = loginService.findByEmail(loginRequest.getEmail());
            return usuarios
                    .map(usuario -> {
                        if (
                                Objects.equals(usuario.getPassw(), loginRequest.getPassw()) &&
                                usuario.getIdRol() == 2
                        ){
                            loginResponse.setUsuario(usuario);
                            loginResponse.setMensaje("Usuario logeado exitosamente");
                            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
                        }
                        else {
                            loginResponse.setMensaje("Usuario erroneo");
                            return new ResponseEntity<>(loginResponse,HttpStatus.MULTI_STATUS);}
                    })
                    .orElseGet(() ->  {
                        loginResponse.setMensaje("Usuario erroneo");
                        return new ResponseEntity<>(loginResponse,HttpStatus.MULTI_STATUS);
                    });
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

