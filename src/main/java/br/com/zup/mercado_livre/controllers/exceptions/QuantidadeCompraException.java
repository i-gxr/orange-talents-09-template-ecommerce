package br.com.zup.mercado_livre.controllers.exceptions;

public class QuantidadeCompraException extends BussinessException {

    public QuantidadeCompraException() {
        super("A quantidade de produtos informados não está disponível em estoque!");
    }

}
