package br.com.zup.mercado_livre.controllers.exceptions;

public class CompraNotFoundException extends BussinessException {

    public CompraNotFoundException() {
        super("A compra informada não existe");
    }

}
