package com.gamje.evaluaciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.gamje.evaluaciones.dto.EvaluacionResponseDTO;
import com.gamje.evaluaciones.model.Curso;
import com.gamje.evaluaciones.model.Evaluacion;
import com.gamje.evaluaciones.model.Usuario;
import com.gamje.evaluaciones.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService service;
    private final WebClient webClient;

    public EvaluacionController(EvaluacionService service, WebClient webClient) {
        this.service = service;
        this.webClient = webClient;
    }

    // Crear evaluación (valida existencia en el service)
    @PostMapping
    @Operation(summary = "Crea una evaluacion",
    description = "Crea una evaluacion en la base de datos (id, nombre, tipo ,nota ,usuarioID, curso)")
    public Evaluacion crear(@RequestBody Evaluacion evaluacion) {
        return service.guardar(evaluacion);
    }

    // Listar todas las evaluaciones (versión sin nombres)
    @GetMapping
    @Operation(summary = "Lista todas las evaluaciones",
    description = "Lista todas las evaluaciones en la base de datos sin sus nombres personalizados(id, nombre, tipo ,nota ,usuarioID, curso) ")
    public List<Evaluacion> listar() {
        return service.obtenerTodas();
    }

    // Evaluaciones por usuario con nombres personalizados

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Lista todas las evaluaciones con sus nombres personalizados",
    description = "Lista todas las evaluaciones en la base de datos con sus respectivos nombres personalizados (id, nombre, tipo, nota, nombreUsuario, nombreCurso)")
    public List<EvaluacionResponseDTO> porUsuario(@PathVariable Integer id) {
        return service.obtenerPorUsuario(id).stream().map(e -> {
            Usuario usuario = webClient.get()
                    .uri("http://localhost:8090/usuarios/" + e.getUsuarioId())
                    .retrieve()
                    .bodyToMono(Usuario.class)
                    .onErrorResume(ex -> Mono.empty())
                    .block();

            Curso curso = webClient.get()
                    .uri("http://localhost:8099/cursos/" + e.getCursoId())
                    .retrieve()
                    .bodyToMono(Curso.class)
                    .onErrorResume(ex -> Mono.empty())
                    .block();

            EvaluacionResponseDTO dto = new EvaluacionResponseDTO();
            dto.setId(e.getId());
            dto.setNombre(e.getNombre());
            dto.setTipo(e.getTipo());
            dto.setNota(e.getNota());
            dto.setNombreUsuario(usuario != null ? usuario.getNombre() : "ND");
            dto.setNombreCurso(curso != null ? curso.getNombreCurso() : "ND");

            return dto;
        }).toList();
    }

    @GetMapping("/curso/{id}")
    @Operation(summary = "Lista todas las evaluaciones con sus nombres personalizados",
    description = "Lista todas las evaluaciones en la base de datos con sus respectivos nombres personalizados  (id, nombre, tipo, nota, nombreUsuario, nombreCurso)")
    public List<EvaluacionResponseDTO> porCurso(@PathVariable Integer id) {
        return service.obtenerPorCurso(id).stream().map(e -> {
            Usuario usuario = webClient.get()
                    .uri("http://localhost:8090/usuarios/" + e.getUsuarioId())
                    .retrieve()
                    .bodyToMono(Usuario.class)
                    .onErrorResume(ex -> Mono.empty())
                    .block();

            Curso curso = webClient.get()
                    .uri("http://localhost:8099/cursos/" + e.getCursoId())
                    .retrieve()
                    .bodyToMono(Curso.class)
                    .onErrorResume(ex -> Mono.empty())
                    .block();

            EvaluacionResponseDTO dto = new EvaluacionResponseDTO();
            dto.setId(e.getId());
            dto.setNombre(e.getNombre());
            dto.setTipo(e.getTipo());
            dto.setNota(e.getNota());
            dto.setNombreUsuario(usuario != null ? usuario.getNombre() : "ND");
            dto.setNombreCurso(curso != null ? curso.getNombreCurso() : "ND");

            return dto;
        }).toList();
}
}
