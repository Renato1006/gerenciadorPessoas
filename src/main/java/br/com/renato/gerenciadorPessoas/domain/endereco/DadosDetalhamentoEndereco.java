package br.com.renato.gerenciadorPessoas.domain.endereco;

public record DadosDetalhamentoEndereco(Long id,
                                        TipoEndereco tipo,
                                        String logradouro,
                                        String bairro,
                                        String cep,
                                        String numero,
                                        String complemento,
                                        String cidade,
                                        String uf,
                                        Long idPessoa) {
    public DadosDetalhamentoEndereco(Endereco endereco){
        this(endereco.getId(), endereco.getTipo(), endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getNumero(), endereco.getComplemento(), endereco.getCidade(), endereco.getUf(), endereco.getPessoa().getId());
    }
}
