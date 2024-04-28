package br.com.renato.gerenciadorPessoas.domain.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroPessoa (
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento){
}
