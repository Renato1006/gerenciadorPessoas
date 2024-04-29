package br.com.renato.gerenciadorPessoas.controller;

import br.com.renato.gerenciadorPessoas.domain.endereco.DadosCadastroEndereco;
import br.com.renato.gerenciadorPessoas.domain.endereco.DadosDetalhamentoEndereco;
import br.com.renato.gerenciadorPessoas.domain.endereco.Endereco;
import br.com.renato.gerenciadorPessoas.domain.endereco.EnderecoRepository;
import br.com.renato.gerenciadorPessoas.domain.pessoa.PessoaRepository;
import br.com.renato.gerenciadorPessoas.infra.exception.ValidacaoException;
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
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroEndereco dados, UriComponentsBuilder uriBuilder){
        if(!pessoaRepository.existsById(dados.idPessoa())){
            throw new ValidacaoException("O id informado para a pessoa não existe");
        }

        var pessoa = pessoaRepository.getReferenceById(dados.idPessoa());

        var endereco = new Endereco(dados, pessoa);

        repository.save(endereco);

        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(endereco.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoEndereco(endereco));
    }
}
