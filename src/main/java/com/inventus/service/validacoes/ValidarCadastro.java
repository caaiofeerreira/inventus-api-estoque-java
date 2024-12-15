package com.inventus.service.validacoes;

import com.inventus.domain.dto.UsuarioDto;
import com.inventus.domain.usuario.UserRole;

import com.inventus.infra.exception.ValidarCadastroException;
import com.inventus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarCadastro {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String NOME_REGEX = "[A-Za-zÀ-ÿ\\s'-]+";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z][a-zA-Z-]*\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String SENHA_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String TELEFONE_REGEX = "\\(\\d{2}\\) \\d{5}-\\d{4}";

    public void validar(UsuarioDto usuarioDto) {

        validarNome(usuarioDto);

        validarEmail(usuarioDto);

        validarSenha(usuarioDto);

        validarTelefone(usuarioDto);

        validarUserRole(usuarioDto);
    }

    private void validarNome(UsuarioDto usuarioDto) {

        String nome  = usuarioDto.nome().trim();

        if (nome.isEmpty()) {
            throw new ValidarCadastroException("O nome é obrigatório.");
        }

        if (nome.length() < 3 || !usuarioDto.nome().matches(NOME_REGEX)) {
            throw new ValidarCadastroException("O nome deve conter pelo menos 3 caracteres e deve conter apenas letras, espaços, hífens e apóstrofos.");
        }
    }

    private void validarEmail(UsuarioDto usuarioDto) {

        usuarioRepository.findByEmailExisting(usuarioDto.email()).ifPresent(email -> {
            throw new ValidarCadastroException("O e-mail fornecido já está associado a uma conta existente.");
        });

        if (usuarioDto.email() == null || usuarioDto.email().isEmpty()) {
            throw new ValidarCadastroException("O e-mail fornecido não pode ser nulo ou vazio.");
        }

        Matcher matcher = EMAIL_PATTERN.matcher(usuarioDto.email());
        if (!matcher.matches()) {
            throw new ValidarCadastroException("O e-mail fornecido é inválido.");
        }

    }

    private void validarSenha(UsuarioDto usuarioDto) {

        if (!usuarioDto.senha().matches(SENHA_REGEX)) {
            throw new ValidarCadastroException("A senha deve ter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
        }
    }

    private void validarTelefone(UsuarioDto usuarioDto) {

        usuarioRepository.findByTelefoneExisting(usuarioDto.telefone().replace(" ", "")).ifPresent(telefone -> {
            throw new ValidarCadastroException("O telefone fornecido já está associado a uma conta existente.");
        });

        if (usuarioDto.telefone().trim().isEmpty()) {
            throw new ValidarCadastroException("O telefone é obrigatório.");

        } else if (!usuarioDto.telefone().matches(TELEFONE_REGEX)) {
            throw new ValidarCadastroException("O telefone deve estar no formato (XX) XXXXX-XXXX");
        }
    }

    private void validarUserRole(UsuarioDto usuarioDto) {
        UserRole userRole = usuarioDto.userRole() == UserRole.ADMIN ? UserRole.ADMIN : UserRole.FUNCIONARIO;
    }
}