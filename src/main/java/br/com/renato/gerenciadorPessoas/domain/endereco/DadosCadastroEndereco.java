package br.com.renato.gerenciadorPessoas.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
        @NotNull
        TipoEndereco tipo,
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        String numero,
        String complemento,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotNull
        Long idPessoa) {
}