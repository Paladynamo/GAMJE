package com.gamje.inscripciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamje.inscripciones.dto.InscripcionDetalleDTO;
import com.gamje.inscripciones.model.Inscripcion;
import com.gamje.inscripciones.service.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    @Operation(summary = "Crea inscripciones",
    description = "Crea inscripciones con los respectivos datos proporcionados (Id, idUsuario e IdCurso)")
    public ResponseEntity<?> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion nueva = inscripcionService.guardar(inscripcion);
            return ResponseEntity.ok(nueva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "lista de todas las inscripciones",
    description = "devuelve una lista de todas las inscripciones registradas (Id, idUsuario e IdCurso)")
    public ResponseEntity<List<Inscripcion>> obtenerTodas() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una inscripcion por ID",
    description = "Obtiene una inscripcion detallada atravez de su ID   (Id, idUsuario e IdCurso)")
    public ResponseEntity<?> obtenerInscripcionPorId(@PathVariable int id) {
        Optional<Inscripcion> inscripcion = inscripcionService.obtenerPorId(id);
        return inscripcion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una inscripcion por ID",
    description = "Elimina una inscripcion especifica del sistema atravez de su ID ")
    public ResponseEntity<String> eliminarInscripcion(@PathVariable int id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.ok("Inscripción eliminada correctamente.");
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtiene inscripciones por usuario",
    description = "Devuelve todas las inscripciones que tiene un usuario (Id, idUsuario e IdCurso)")
    public ResponseEntity<List<Inscripcion>> obtenerPorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.ok(inscripcionService.obtenerPorUsuario(idUsuario));
    }

    @GetMapping("/curso/{idCurso}")
    @Operation(summary = "Obtiene inscripciones por curso",
    description = "Devuelve todas las incripciones relacionadas a un curso (Id, idUsuario e IdCurso)")
    public ResponseEntity<List<Inscripcion>> obtenerPorCurso(@PathVariable int idCurso) {
        return ResponseEntity.ok(inscripcionService.obtenerPorCurso(idCurso));
    }

    // 🔎 Consultas con detalles
    @GetMapping("/detalles")
    @Operation(summary = "Obtiene los detalles de las incripciones",
    description = "Devuelve una lista de las inscripciones con sus respectivos destalles (Id, usuario, id, nombre, email, rut, rol) (Curso, id curso, nombre curso, descripcion curso, docente curso, durancion curso)")
    public ResponseEntity<List<InscripcionDetalleDTO>> obtenerConDetalles() {
        return ResponseEntity.ok(inscripcionService.obtenerTodasConDetalles());
    }

    @GetMapping("/detalle/{id}")
    @Operation(summary = "Obtiene detalle de una inscripcion por su ID",
    description = "Devuelve detalles adicionales de una inscripcion especifica usando su ID  (Id, usuario, id, nombre, email, rut, rol)")
    public ResponseEntity<?> obtenerDetalleInscripcion(@PathVariable int id) {
        try {
            InscripcionDetalleDTO detalle = inscripcionService.obtenerDetalleInscripcion(id);
            return ResponseEntity.ok(detalle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Mantenemos solo uno para evitar duplicado
    @GetMapping("/detalle/por-usuario/{idUsuario}")
    @Operation(summary = "Obtiene detalle de inscripciones por usuario",
    description = "Obtiene detalle completo de la inscripciones de un usuario (Id, usuario, id, nombre, email, rut, rol) (Curso, id curso, nombre curso, descripcion curso, docente curso, durancion curso)")
    public ResponseEntity<List<InscripcionDetalleDTO>> obtenerDetallePorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.ok(inscripcionService.obtenerInscripcionesDetalladasPorUsuario(idUsuario));
    }

    @GetMapping("/detalle/por-curso/{idCurso}")
    @Operation(summary = "Obtiene detalle de inscripciones por curso",
    description = "Obtiene detalle completo de las inscripciones de un curso (Id, usuario, id, nombre, email, rut, rol) (Curso, id curso, nombre curso, descripcion curso, docente curso, durancion curso)")
    public ResponseEntity<List<InscripcionDetalleDTO>> obtenerDetallePorCurso(@PathVariable int idCurso) {
        return ResponseEntity.ok(inscripcionService.obtenerDetallePorCurso(idCurso));
    }
}
