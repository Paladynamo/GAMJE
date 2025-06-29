package com.gamje.evaluaciones.repository;

import com.gamje.evaluaciones.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByUsuarioId(Integer usuarioId);
    List<Evaluacion> findByCursoId(Integer cursoId);
}