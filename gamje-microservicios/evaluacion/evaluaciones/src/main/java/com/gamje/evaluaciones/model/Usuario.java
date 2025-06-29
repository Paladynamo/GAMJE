package com.gamje.evaluaciones.model;

import lombok.Data;

@Data
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String rut;
    private String rol;
}
