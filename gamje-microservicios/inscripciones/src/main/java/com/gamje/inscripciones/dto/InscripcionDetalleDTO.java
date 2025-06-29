package com.gamje.inscripciones.dto;

import com.gamje.inscripciones.model.Curso;
import com.gamje.inscripciones.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDetalleDTO {
    private int id;
    private Usuario usuario;
    private Curso curso;
}

