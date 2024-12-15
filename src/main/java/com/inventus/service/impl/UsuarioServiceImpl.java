package com.inventus.service.impl;

import com.inventus.domain.dto.UsuarioDto;
import com.inventus.domain.usuario.Usuario;
import com.inventus.repository.UsuarioRepository;
import com.inventus.service.UsuarioService;
import com.inventus.service.validacoes.ValidarCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidarCadastro validarCadastro;

    @Override
    @Transactional
    public UsuarioDto cadastrarUsuario(UsuarioDto usuarioDto) {

        validarCadastro.validar(usuarioDto);

        String senha = passwordEncoder.encode(usuarioDto.senha());

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        usuario.setSenha(senha);
        usuario.setTelefone(usuarioDto.telefone().replace(" ", ""));
        usuario.setUserRole(usuarioDto.userRole());
        usuario.setDataCadastro(LocalDate.now());

        Usuario novoUsuario = usuarioRepository.save(usuario);

        return new UsuarioDto(novoUsuario);
    }
}