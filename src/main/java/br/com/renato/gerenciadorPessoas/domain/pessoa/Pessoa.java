package br.com.renato.gerenciadorPessoas.domain.pessoa;

import br.com.renato.gerenciadorPessoas.domain.endereco.Endereco;
import jakarta.persistence.*;

import java.time.LocalDate;

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany
    private Endereco[] enderecos;

}
