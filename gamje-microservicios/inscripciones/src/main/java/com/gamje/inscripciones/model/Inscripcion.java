package com.gamje.inscripciones.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inscripciones")
@Data
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Column(name = "id_curso", nullable = false)
    private int idCurso;
}