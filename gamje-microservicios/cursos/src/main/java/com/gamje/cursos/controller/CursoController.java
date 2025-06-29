package com.gamje.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamje.cursos.client.UsuarioClient;
import com.gamje.cursos.model.Curso;
import com.gamje.cursos.model.Usuario;
import com.gamje.cursos.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final UsuarioClient usuarioClient;

    public CursoController(CursoService cursoService, UsuarioClient usuarioClient) {
        this.cursoService = cursoService;
        this.usuarioClient = usuarioClient;
    }

    @PostMapping
    @Operation(summary = "Crea un curso",
    description = "Crea un curso en la base de dato con los datos obtenidos (Id curso, nombre curso, descripcion curso, docente curso, durancion curso)")
    public ResponseEntity<?> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.guardar(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los cursos",
    description = "Obtiene una lista de todos los cursos registrados (Id curso, nombre curso, descripcion curso, docente curso, durancio curso)")
    public ResponseEntity<List<Curso>> obtenerTodos() {
        return ResponseEntity.ok(cursoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Muestra el curso de su ID",
    description = "Obtiene el curso con su respectiva ID proporcionado(ID curso, descripcion curso, Nombre curso, Docente curso, duracion)")
    public ResponseEntity<?> obtenerCursoPorId(@PathVariable int id) {
        Optional<Curso> curso = cursoService.obtenerPorId(id);
        return curso.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un curso",
    description = "Elimina un curso por su ID y dando mensajes correspondente")
    public ResponseEntity<String> eliminarCurso(@PathVariable int id) {
        cursoService.eliminar(id);
        return ResponseEntity.ok("Curso eliminado correctamente.");
    }

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Obtiene un usuario por su ID",
    description = "Busca en el microservicio de usuario para optener los datos relacionados a el curso usando la ID(ID, rol, rut, nombre, email, password)")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioClient.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }
}