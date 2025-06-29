package com.gamje.inscripciones.client;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.gamje.inscripciones.model.Usuario;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081/usuarios").build();
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Usuario.class)
                .block();
    }
}