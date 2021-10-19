package br.com.zup.mercado_livre.controllers.exceptions;

public class TransacaoJaExistenteException extends BussinessException {

    public TransacaoJaExistenteException() {
        super("Já existe uma transação igual sendo processada para essa compra!");
    }

}
