package br.com.renato.gerenciadorPessoas.domain.pessoa;

import java.time.LocalDate;

public record DadosDetalhamentoPessoa(Long id, String nome, LocalDate dataNascimento) {
    public DadosDetalhamentoPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento());
    }
}
