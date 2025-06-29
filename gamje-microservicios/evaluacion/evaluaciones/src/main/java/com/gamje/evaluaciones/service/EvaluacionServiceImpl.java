package com.gamje.evaluaciones.service;

import com.gamje.evaluaciones.model.Curso;
import com.gamje.evaluaciones.model.Evaluacion;
import com.gamje.evaluaciones.model.Usuario;
import com.gamje.evaluaciones.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository repository;
    private final WebClient webClient;

    public EvaluacionServiceImpl(EvaluacionRepository repository, WebClient webClient) {
        this.repository = repository;
        this.webClient = webClient;
    }

    @Override
    public Evaluacion guardar(Evaluacion evaluacion) {
        // Validar existencia del usuario
        Usuario usuario = webClient.get()
                .uri("http://localhost:8090/usuarios/" + evaluacion.getUsuarioId())
                .retrieve()
                .bodyToMono(Usuario.class)
                .onErrorResume(e -> Mono.empty()) // evita que falle si no existe
                .block();

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con ID: " + evaluacion.getUsuarioId());
        }

        // Validar existencia del curso
        Curso curso = webClient.get()
                .uri("http://localhost:8099/cursos/" + evaluacion.getCursoId())
                .retrieve()
                .bodyToMono(Curso.class)
                .onErrorResume(e -> Mono.empty())
                .block();

        if (curso == null) {
            throw new RuntimeException("Curso no encontrado con ID: " + evaluacion.getCursoId());
        }

        // Si ambos existen, guardar
        return repository.save(evaluacion);
    }

    @Override
    public List<Evaluacion> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<Evaluacion> obtenerPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Evaluacion> obtenerPorCurso(Integer cursoId) {
        return repository.findByCursoId(cursoId);
    }
}