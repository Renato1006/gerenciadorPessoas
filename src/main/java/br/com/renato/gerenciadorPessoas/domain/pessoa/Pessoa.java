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

    public Pessoa(DadosCadastroPessoa dados){
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
    }
}
