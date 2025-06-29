package com.gamje.evaluaciones.service;

import com.gamje.evaluaciones.model.Evaluacion;

import java.util.List;

public interface EvaluacionService {
    Evaluacion guardar(Evaluacion evaluacion);
    List<Evaluacion> obtenerTodas();
    List<Evaluacion> obtenerPorUsuario(Integer usuarioId);
    List<Evaluacion> obtenerPorCurso(Integer cursoId);
}