package com.gamje.inscripciones.client;



import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.gamje.inscripciones.model.Curso;

@Component
public class CursoClient {

    private final WebClient webClient;

    public CursoClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8099/cursos").build();
    }

    public Curso obtenerCursoPorId(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Curso.class)
                .block();
    }
    
}