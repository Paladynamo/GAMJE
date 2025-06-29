package com.gamje.pagos.model;

import lombok.Data;

@Data
public class Curso {
    private int idCurso;
    private String nombreCurso;
    private String descripcionCurso;
    private String docenteCurso;
    private String duracionCurso;
}