package com.gamje.pagos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PagoDetalleDTO {
    private int id;
    private double monto;
    private String nombreUsuario;
    private String nombreCurso;
    private LocalDateTime fecha;
}