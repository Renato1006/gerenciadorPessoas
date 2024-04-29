package br.com.renato.gerenciadorPessoas.domain.pessoa;

import java.time.LocalDate;

public record DadosListagemPessoa(Long id, String nome, LocalDate dataNascimento) {
    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento());
    }
}
