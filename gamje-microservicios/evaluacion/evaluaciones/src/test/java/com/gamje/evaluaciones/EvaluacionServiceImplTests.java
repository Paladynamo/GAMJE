package com.gamje.evaluaciones;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.gamje.evaluaciones.model.Evaluacion;
import com.gamje.evaluaciones.repository.EvaluacionRepository;
import com.gamje.evaluaciones.service.EvaluacionServiceImpl;


class EvaluacionServiceImplTests {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionServiceImpl evaluacionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void probarObtenerTodos() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombre("Prueba 1");
        evaluacion.setTipo("Prueba");
        evaluacion.setNota(7.0);
        evaluacion.setUsuarioId(1);
        evaluacion.setCursoId(1);

        when(evaluacionRepository.findAll()).thenReturn(Arrays.asList(evaluacion));

        List<Evaluacion> lista = evaluacionServiceImpl.obtenerTodas();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Prueba 1", lista.get(0).getNombre());
        assertEquals(7.0, lista.get(0).getNota());
    }
}
