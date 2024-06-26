package br.com.renato.gerenciadorPessoas.domain.pessoa;

import br.com.renato.gerenciadorPessoas.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "pessoa")
@Entity(name = "pessoa")
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Endereco[] enderecos;

    private Boolean ativo;

    public Pessoa(DadosCadastroPessoa dados){
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoPessoa dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }

        if (dados.dataNascimento() != null){
            this.dataNascimento = dados.dataNascimento();
        }
    }
}
