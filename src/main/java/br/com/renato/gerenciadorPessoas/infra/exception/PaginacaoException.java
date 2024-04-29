package br.com.renato.gerenciadorPessoas.infra.exception;

public class PaginacaoException extends RuntimeException{
    public PaginacaoException(String ex){
        super(ex);
    }
}
