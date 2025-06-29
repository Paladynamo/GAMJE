package com.gamje.evaluaciones.dto;

import lombok.Data;

@Data
public class EvaluacionResponseDTO {
    private Long id;
    private String nombre;         // nombre de la evaluación
    private String tipo;           // tipo de evaluación (prueba, tarea, etc.)
    private Double nota;

    private String nombreUsuario;  // se obtiene vía WebClient
    private String nombreCurso;    // se obtiene vía WebClient
}