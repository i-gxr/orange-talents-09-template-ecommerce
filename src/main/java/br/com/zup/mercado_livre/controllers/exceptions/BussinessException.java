package br.com.zup.mercado_livre.controllers.exceptions;

public class BussinessException extends RuntimeException {

    public BussinessException(String message) {
        super(message);
    }

}
