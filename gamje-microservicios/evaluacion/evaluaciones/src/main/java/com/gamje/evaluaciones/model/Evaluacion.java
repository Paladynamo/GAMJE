package com.gamje.evaluaciones.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo; // prueba, tarea, proyecto, etc.
    private Double nota;

    private Integer usuarioId;
    private Integer cursoId;
}