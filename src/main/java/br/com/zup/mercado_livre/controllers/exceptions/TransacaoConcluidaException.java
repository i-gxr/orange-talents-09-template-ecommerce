package br.com.zup.mercado_livre.controllers.exceptions;

public class TransacaoConcluidaException extends BussinessException {

    public TransacaoConcluidaException() {
        super("A transação dessa compra já foi concluída!");
    }

}
