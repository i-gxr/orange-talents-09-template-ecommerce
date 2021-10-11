package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.models.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.*;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @Deprecated
    public PerguntaRequest() {}

    public PerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Pergunta toModel(Produto produto, Usuario usuario) {
        return new Pergunta(this.titulo, produto, usuario);
    }

}
