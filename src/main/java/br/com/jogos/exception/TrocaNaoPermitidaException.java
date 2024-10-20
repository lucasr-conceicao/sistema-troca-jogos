package br.com.jogos.exception;

public class TrocaNaoPermitidaException extends RuntimeException {
    public TrocaNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}
