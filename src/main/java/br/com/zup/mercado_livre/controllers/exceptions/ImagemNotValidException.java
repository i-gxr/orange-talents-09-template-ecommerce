package br.com.zup.mercado_livre.controllers.exceptions;

public class ImagemNotValidException extends BussinessException {

    public ImagemNotValidException() {
        super("A imagem recebida não é válida!");
    }

}
