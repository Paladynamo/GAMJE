package com.gamje.inscripciones.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamje.inscripciones.client.UsuarioClient;
import com.gamje.inscripciones.dto.InscripcionDetalleDTO;
import com.gamje.inscripciones.client.CursoClient;
import com.gamje.inscripciones.model.Inscripcion;
import com.gamje.inscripciones.model.Usuario;
import com.gamje.inscripciones.model.Curso;
import com.gamje.inscripciones.repository.InscripcionRepository;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final UsuarioClient usuarioClient;
    private final CursoClient cursoClient;

    public InscripcionService(
        InscripcionRepository inscripcionRepository,
        UsuarioClient usuarioClient,
        CursoClient cursoClient
    ) {
        this.inscripcionRepository = inscripcionRepository;
        this.usuarioClient = usuarioClient;
        this.cursoClient = cursoClient;
    }

    public Inscripcion guardar(Inscripcion inscripcion) throws Exception {
        // Validar existencia del usuario
        Usuario usuario = usuarioClient.obtenerUsuarioPorId(inscripcion.getIdUsuario());
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        // Validar existencia del curso
        Curso curso = cursoClient.obtenerCursoPorId(inscripcion.getIdCurso());
        if (curso == null) {
            throw new Exception("Curso no encontrado");
        }

        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerPorId(int id) {
        return inscripcionRepository.findById(id);
    }

    public void eliminar(int id) {
        inscripcionRepository.deleteById(id);
    }
    public List<Inscripcion> obtenerPorUsuario(int idUsuario) {
    return inscripcionRepository.findByIdUsuario(idUsuario);
}

    public List<Inscripcion> obtenerPorCurso(int idCurso) {
        return inscripcionRepository.findByIdCurso(idCurso);
    }
    
    public List<InscripcionDetalleDTO> obtenerTodasConDetalles() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();

        return inscripciones.stream().map(insc -> {
            Usuario usuario = usuarioClient.obtenerUsuarioPorId(insc.getIdUsuario());
            Curso curso = cursoClient.obtenerCursoPorId(insc.getIdCurso());

            return new InscripcionDetalleDTO(insc.getId(), usuario, curso);
        }).toList();
    }
    public InscripcionDetalleDTO obtenerDetalleInscripcion(int id) throws Exception {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new Exception("Inscripción no encontrada"));

        Usuario usuario = usuarioClient.obtenerUsuarioPorId(inscripcion.getIdUsuario());
        Curso curso = cursoClient.obtenerCursoPorId(inscripcion.getIdCurso());

        return new InscripcionDetalleDTO(inscripcion.getId(), usuario, curso);
    }
    public List<Inscripcion> obtenerPorIdUsuario(int idUsuario) {
    return inscripcionRepository.findByIdUsuario(idUsuario);
    }

    public List<Inscripcion> obtenerPorIdCurso(int idCurso) {
        return inscripcionRepository.findByIdCurso(idCurso);
    }
    public List<InscripcionDetalleDTO> obtenerDetallePorUsuario(int idUsuario) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByIdUsuario(idUsuario);
        List<InscripcionDetalleDTO> detalles = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            Usuario usuario = usuarioClient.obtenerUsuarioPorId(inscripcion.getIdUsuario());
            Curso curso = cursoClient.obtenerCursoPorId(inscripcion.getIdCurso());

            InscripcionDetalleDTO dto = new InscripcionDetalleDTO(
                inscripcion.getId(),
                usuario,
                curso
            );
            detalles.add(dto);
        }

        return detalles;
    }
        public List<InscripcionDetalleDTO> obtenerDetallePorCurso(int idCurso) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByIdCurso(idCurso);
        List<InscripcionDetalleDTO> detalles = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            Usuario usuario = usuarioClient.obtenerUsuarioPorId(inscripcion.getIdUsuario());
            Curso curso = cursoClient.obtenerCursoPorId(inscripcion.getIdCurso());

            InscripcionDetalleDTO dto = new InscripcionDetalleDTO(
                inscripcion.getId(),
                usuario,
                curso
            );
            detalles.add(dto);
        }

        return detalles;
    }
    public List<InscripcionDetalleDTO> obtenerInscripcionesDetalladasPorUsuario(int idUsuario) {
    List<Inscripcion> inscripciones = inscripcionRepository.findByIdUsuario(idUsuario);
    List<InscripcionDetalleDTO> detalles = new ArrayList<>();

    for (Inscripcion inscripcion : inscripciones) {
        Usuario usuario = usuarioClient.obtenerUsuarioPorId(inscripcion.getIdUsuario());
        Curso curso = cursoClient.obtenerCursoPorId(inscripcion.getIdCurso());

        InscripcionDetalleDTO dto = new InscripcionDetalleDTO(
            inscripcion.getId(),
            usuario,
            curso
        );
        detalles.add(dto);
    }

    return detalles;
}

    
    
}
