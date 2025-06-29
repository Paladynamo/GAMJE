package com.gamje.inscripciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gamje.inscripciones.model.Inscripcion;
import com.gamje.inscripciones.service.InscripcionService;

@SpringBootTest
    class InscripcionServiceTests {
    
    
    @Autowired
    private InscripcionService inscripcionservice;


    @Test
    void probarGuardar() throws Exception {
    Inscripcion inscripcion = new Inscripcion();
    inscripcion.setIdUsuario(3);
    inscripcion.setIdCurso(2);

    Inscripcion guardado = inscripcionservice.guardar(inscripcion);

    assertNotNull(guardado.getId());
    assertEquals(3, guardado.getIdUsuario());
    assertEquals(2, guardado.getIdCurso());
    }
}
