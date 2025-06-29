package com.gamje.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gamje.usuarios.service.UsuarioService;

@SpringBootTest
public class UsuarioServicetest {
    

    @Autowired
    private UsuarioService usuarioService;


    @Test
    void probarHashear(){
        String password = "Hola123";
        String hash = usuarioService.hashearPassword(password);
        boolean coincide = usuarioService.comprobarPassword(hash, password);
        assertEquals(true, coincide);
    }
}
