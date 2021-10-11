package br.com.zup.mercado_livre.controllers.exceptions;

public class ProdutoForbiddenException extends BussinessException {

    public ProdutoForbiddenException() {
        super("O produto informado não é correspondente ao usuário!");
    }

}
