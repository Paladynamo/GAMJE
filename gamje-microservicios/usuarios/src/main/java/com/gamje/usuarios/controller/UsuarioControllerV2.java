package com.gamje.usuarios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gamje.usuarios.assemblers.UserModelAssembler;
import com.gamje.usuarios.model.entities.Usuario;
import com.gamje.usuarios.dto.LoginRequest;
import com.gamje.usuarios.service.UsuarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("v2/usuarios")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;

    public UsuarioControllerV2(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    private UserModelAssembler assemblers;

    @PostMapping
    @Operation(summary = "Crea un usuario", 
    description = "Crea a un usuario en la base de datos con los atributos (Rol, RUT, Nombre, Email y Password) usando try catch para evitar errores y devolviendo un mensaje de error")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Devuelve todos los usuario", 
    description = "Obtiene todos los valores de un usuario de la base de datos en forma de lista")

    public CollectionModel<EntityModel<Usuario>> obtenerTodos() {
        List<EntityModel<Usuario>> usuarios = usuarioService.obtenerTodos().stream()
            .map(assemblers::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            linkTo(methodOn(UsuarioControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Devuelve solo un usuario", 
    description = "Devuelve un usuario de la base de datos buscando su ID, retornando codigo de estado 404 (Not found)")

    public ResponseEntity<EntityModel<Usuario>> obtenerUsuarioPorId(@PathVariable int id) {
    return usuarioService.obtenerPorId(id)
        .map(usuario -> ResponseEntity.ok(assemblers.toModel(usuario)))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario", 
    description = "Elimina un usuario de la base de datos usando su ID y devolviendo el correspondiente mensaje de eliminacion")

    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    @PostMapping("/login")
    @Operation(
    summary = "Iniciar sesión",
    description = "Autentica al usuario mediante email y contraseña, Retorna los datos del usuario si las credenciales son validas.")

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    
}
