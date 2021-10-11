package br.com.zup.mercado_livre.controllers.exceptions;

public class UsuarioNotFoundException extends BussinessException {

    public UsuarioNotFoundException() {
        super("O usuário que está fazendo a requisição não foi encontrado!");
    }

}
