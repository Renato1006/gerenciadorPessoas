package br.com.renato.gerenciadorPessoas.infra.exception;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String s) {
        super(s);
    }
}
