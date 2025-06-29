package com.gamje.pagos.service;

import com.gamje.pagos.dto.PagoDetalleDTO;
import com.gamje.pagos.model.*;
import com.gamje.pagos.repository.PagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final WebClient webClient;

    public PagoService(PagoRepository pagoRepository, WebClient webClient) {
        this.pagoRepository = pagoRepository;
        this.webClient = webClient;
    }

    public Pago registrarPago(Pago pago) throws Exception {
        Usuario usuario = webClient.get()
            .uri("http://localhost:8081/usuarios/{id}", pago.getIdUsuario())
            .retrieve()
            .bodyToMono(Usuario.class)
            .block();

        if (usuario == null) throw new Exception("Usuario no encontrado");

        Curso curso = webClient.get()
            .uri("http://localhost:8099/cursos/{id}", pago.getIdCurso())
            .retrieve()
            .bodyToMono(Curso.class)
            .block();

        if (curso == null) throw new Exception("Curso no encontrado");

        pago.setFecha(LocalDateTime.now());
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPorId(int id) {
        return pagoRepository.findById(id);
    }

    public void eliminar(int id) {
        pagoRepository.deleteById(id);
    }

    public List<Pago> obtenerPorUsuario(int idUsuario) {
        return pagoRepository.findByIdUsuario(idUsuario);
    }

    public List<Pago> obtenerPorCurso(int idCurso) {
        return pagoRepository.findByIdCurso(idCurso);
    }
    public PagoDetalleDTO obtenerDetallePorId(int id) throws Exception {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new Exception("Pago no encontrado"));

        Usuario usuario = webClient.get()
                .uri("http://localhost:8081/usuarios/{id}", pago.getIdUsuario())
                .retrieve()
                .bodyToMono(Usuario.class)
                .block();

        Curso curso = webClient.get()
                .uri("http://localhost:8099/cursos/{id}", pago.getIdCurso())
                .retrieve()
                .bodyToMono(Curso.class)
                .block();

        return new PagoDetalleDTO(
            pago.getId(),
            pago.getMonto(),
            usuario.getNombre(),
            curso.getNombreCurso(),
            pago.getFecha()
        );
    }
    public List<PagoDetalleDTO> obtenerTodosConDetalles() {
        List<Pago> pagos = pagoRepository.findAll();
        List<PagoDetalleDTO> detalles = new ArrayList<>();

        for (Pago pago : pagos) {
            try {
                Usuario usuario = webClient.get()
                    .uri("http://localhost:8081/usuarios/{id}", pago.getIdUsuario())
                    .retrieve()
                    .bodyToMono(Usuario.class)
                    .block();

                Curso curso = webClient.get()
                    .uri("http://localhost:8099/cursos/{id}", pago.getIdCurso())
                    .retrieve()
                    .bodyToMono(Curso.class)
                    .block();

                detalles.add(new PagoDetalleDTO(
                    pago.getId(),
                    pago.getMonto(),
                    usuario != null ? usuario.getNombre() : "ND",
                    curso != null ? curso.getNombreCurso() : "ND",
                    pago.getFecha()
                ));
            } catch (Exception e) {
                // si falla alguna llamada, igual agrega el pago con ND
                detalles.add(new PagoDetalleDTO(
                    pago.getId(),
                    pago.getMonto(),
                    "ND",
                    "ND",
                    pago.getFecha()
                ));
            }
        }

        return detalles;
    }
}
