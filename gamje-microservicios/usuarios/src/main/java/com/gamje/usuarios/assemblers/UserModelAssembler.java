package com.gamje.usuarios.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.server.RepresentationModelAssembler;


import com.gamje.usuarios.model.entities.Usuario;
import com.gamje.usuarios.controller.UsuarioControllerV2;

@Component
public class UserModelAssembler  implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{
    
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario){
        return EntityModel.of(usuario,
        linkTo(methodOn(UsuarioControllerV2.class).obtenerUsuarioPorId(usuario.getId())).withSelfRel(),
        linkTo(methodOn(UsuarioControllerV2.class).obtenerTodos()).withRel("Usuario"));
    }
}
