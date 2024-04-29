package br.com.renato.gerenciadorPessoas.domain.endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    @Query("""
            select e from Endereco e
            where
            e.pessoa.id = :id
            """)
    Page<Endereco> buscaTodosEnderecos(Long id,Pageable pageable);

}
