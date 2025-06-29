package com.gamje.cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gamje.cursos.model.Curso;
import com.gamje.cursos.service.CursoService;
    

    @SpringBootTest
    class CursoServiceTests {

    @Autowired
    private CursoService cursoService;

    @Test
    void probarGuardar(){
        Curso curso = new Curso();
        curso.setNombreCurso("Python");
        curso.setDescripcionCurso("Python basico desde variables hasta funciones");
        curso.setDocenteCurso("Moly Castillo");
        curso.setDuracionCurso("40 horas");

        Curso guardado = cursoService.guardar(curso);

        assertNotNull(guardado.getIdCurso(), "El ID no debe ser nulo");
        assertEquals("Python", guardado.getNombreCurso());
        assertEquals("Python basico desde variables hasta funciones", guardado.getDescripcionCurso());
        assertEquals("Moly Castillo", guardado.getDocenteCurso());
        assertEquals("40 horas", guardado.getDuracionCurso());
    }
}
