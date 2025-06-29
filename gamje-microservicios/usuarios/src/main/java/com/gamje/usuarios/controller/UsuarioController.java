package com.gamje.usuarios.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gamje.usuarios.dto.LoginRequest;
import com.gamje.usuarios.model.entities.Usuario;
import com.gamje.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

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
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Devuelve solo un usuario", 
    description = "Devuelve un usuario de la base de datos buscando su ID, retornando codigo de estado 404 (Not found)")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario", 
    description = "Elimina un usuario de la base de datos usando su ID y devolviendo el correspondiente mensaje de eliminacion")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión",
    description = "Autentica al usuario mediante email y contraseña, retorna los datos del usuario si las credenciales son validas.")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    
}
