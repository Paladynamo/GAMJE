package com.gamje.inscripciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private int idCurso;
    private String nombreCurso;
    private String descripcionCurso;
    private String docenteCurso;
    private String duracionCurso;
}