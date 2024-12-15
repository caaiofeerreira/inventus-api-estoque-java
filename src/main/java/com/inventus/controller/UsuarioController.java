package com.inventus.controller;

import com.inventus.domain.dto.UsuarioDto;
import com.inventus.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventus")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioService.cadastrarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sua conta foi cadastrada com sucesso.");
    }
}