package com.gamje.pagos.controller;

import com.gamje.pagos.dto.PagoDetalleDTO;
import com.gamje.pagos.model.Pago;
import com.gamje.pagos.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    @Operation (summary = "Registra un nuevo pago",
    description = "Registra un nuevo pago en el sistema con los siguientes atributos (Id, IdUsuario, IdCurso,Monto, fecha)")
    public ResponseEntity<?> crearPago(@RequestBody Pago pago) {
        try {
            return ResponseEntity.ok(pagoService.registrarPago(pago));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation (summary = "Devuelve todos los pagos",
    description = "Devuelve una lista de todos los pagos en el sistema cada uno con los siguientes atributos (Id, IdUsuario, IdCurso,Monto, fecha)")
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un pago por su ID",
    description = "Devuelve un pago especifico con su ID con los siguientes atributos (Id, IdUsuario, IdCurso,Monto, fecha)")
    public ResponseEntity<?> porId(@PathVariable int id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un pago",
    description = "Elimina un pago especifico de la base de datos atravez de su ID y retorna mensaje correspondiente")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        pagoService.eliminar(id);
        return ResponseEntity.ok("Pago eliminado.");
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Lista todos los pagos de un usuario",
    description = "Devuelve una lista de todos los pagos de un mismo usuario con los siguientes atributos (Id, IdUsuario, IdCurso,Monto, fecha)")
    public ResponseEntity<List<Pago>> pagosPorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.ok(pagoService.obtenerPorUsuario(idUsuario));
    }

    @GetMapping("/curso/{idCurso}")
    @Operation(summary = "Lista de pagos por curso",
    description = "Obtiene todos los pagos asociados a un curso  (ID, Monto, NombreUsuario, NombreCurso, Fecha)")
    public ResponseEntity<List<Pago>> pagosPorCurso(@PathVariable int idCurso) {
        return ResponseEntity.ok(pagoService.obtenerPorCurso(idCurso));
    }

    @GetMapping("/detalle/{id}")
    @Operation(summary = "Obtiene detalles de un pago",
    description = "Obtiene de forma mas detallada un pago por su respectivo ID  (ID, Monto, NombreUsuario, NombreCurso, Fecha)")
    public ResponseEntity<?> obtenerDetalle(@PathVariable int id) {
        try {
            return ResponseEntity.ok(pagoService.obtenerDetallePorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detalles")
    @Operation(summary = "Obtiene todos los pagos con detalles",
    description = "Retorna todos los pagos con sus respectivos detalles (ID, Monto, NombreUsuario, NombreCurso, Fecha)")
    public ResponseEntity<List<PagoDetalleDTO>> obtenerTodosConDetalles() {
        return ResponseEntity.ok(pagoService.obtenerTodosConDetalles());
    }
}
