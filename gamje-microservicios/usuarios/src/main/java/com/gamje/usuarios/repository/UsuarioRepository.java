package com.gamje.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gamje.usuarios.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByRut(String rut);

    boolean existsByEmail(String email);
    boolean existsByRut(String rut);
}


