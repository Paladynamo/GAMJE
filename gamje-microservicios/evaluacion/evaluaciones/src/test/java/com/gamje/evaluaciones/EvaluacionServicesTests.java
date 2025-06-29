package com.gamje.evaluaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gamje.evaluaciones.model.Evaluacion;
import com.gamje.evaluaciones.service.EvaluacionService;

    @SpringBootTest
    class EvaluacionServicesTests {
    
    @Autowired
    private EvaluacionService evaluacionservice;

    @Test
    void probarGuardarEvaluacion(){
    Evaluacion evaluacion = new Evaluacion();
    evaluacion.setNombre("Tarea 1");
    evaluacion.setTipo("Tarea");
    evaluacion.setNota(7.0);
    evaluacion.setUsuarioId(1);
    evaluacion.setCursoId(1);

    Evaluacion guardada = evaluacionservice.guardar(evaluacion);

    assertNotNull(guardada);
    assertEquals("Tarea 1", guardada.getNombre());
    assertEquals(9.0, guardada.getNota());
    assertEquals(1, guardada.getUsuarioId());
    assertEquals(1, guardada.getCursoId());
    }
}
