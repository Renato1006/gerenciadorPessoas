package br.com.renato.gerenciadorPessoas.controller;

import br.com.renato.gerenciadorPessoas.domain.pessoa.DadosCadastroPessoa;
import br.com.renato.gerenciadorPessoas.domain.pessoa.DadosDetalhamentoPessoa;
import br.com.renato.gerenciadorPessoas.domain.pessoa.Pessoa;
import br.com.renato.gerenciadorPessoas.domain.pessoa.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoa dados, UriComponentsBuilder uriBuilder){
        var pessoa = new Pessoa(dados);

        repository.save(pessoa);

        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPessoa(pessoa));
    }
}
