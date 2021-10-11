package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.models.*;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Produto produto, Usuario usuario) {
        return new Opiniao(this.nota, this.titulo, this.descricao, produto, usuario);
    }

}
