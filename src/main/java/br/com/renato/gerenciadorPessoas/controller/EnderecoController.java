package br.com.renato.gerenciadorPessoas.controller;

import br.com.renato.gerenciadorPessoas.domain.endereco.*;
import br.com.renato.gerenciadorPessoas.domain.pessoa.PessoaRepository;
import br.com.renato.gerenciadorPessoas.infra.exception.PaginacaoException;
import br.com.renato.gerenciadorPessoas.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<Page<DadosDetalhamentoEndereco>> listar(@PathVariable Long id, Pageable pageable){
        var page = repository.buscaTodosEnderecos(id,pageable).map(DadosDetalhamentoEndereco::new);

        if (page.isEmpty()){
            throw new PaginacaoException("Não existe endereço cadastrado desta pessoa");
        }

        return ResponseEntity.ok(page);
    }
}
