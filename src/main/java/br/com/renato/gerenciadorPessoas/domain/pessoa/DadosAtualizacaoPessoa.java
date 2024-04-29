package br.com.renato.gerenciadorPessoas.domain.pessoa;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoPessoa(
        @NotNull
        Long id,
        String nome,
        LocalDate dataNascimento
) {
}
