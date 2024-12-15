package com.inventus.domain.dto;

import com.inventus.domain.usuario.UserRole;
import com.inventus.domain.usuario.Usuario;

public record UsuarioDto(Long id,
                         String nome,
                         String email,
                         String senha,
                         String telefone,
                         UserRole userRole) {

    public UsuarioDto(Usuario usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTelefone(),
                usuario.getUserRole());
    }
}