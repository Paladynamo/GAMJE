package com.gamje.inscripciones.repository;

import com.gamje.inscripciones.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByIdUsuario(int idUsuario);
    List<Inscripcion> findByIdCurso(int idCurso);
}