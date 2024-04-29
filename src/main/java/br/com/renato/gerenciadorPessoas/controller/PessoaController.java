package br.com.renato.gerenciadorPessoas.controller;

import br.com.renato.gerenciadorPessoas.domain.pessoa.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoa>> listar(Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemPessoa::new);

        if (page.isEmpty()){
            throw new PaginacaoException("Não existe pessoas cadastradas!");
        }

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPessoa dados){
        var pessoa = repository.getReferenceById(dados.id());

        pessoa.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id){
        if (!repository.existsById(id)){
            throw new ValidacaoException("Pessoa não encontrada!");
        }

        var pessoa = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }
}
