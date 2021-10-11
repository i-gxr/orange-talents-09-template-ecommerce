package br.com.zup.mercado_livre.controllers.exceptions;

public class ProdutoNotFoundException extends BussinessException {

    public ProdutoNotFoundException() {
        super("O produto informado não existe!");
    }

}
