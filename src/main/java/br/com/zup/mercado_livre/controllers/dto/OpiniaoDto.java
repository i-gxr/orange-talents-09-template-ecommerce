package br.com.zup.mercado_livre.controllers.dto;

public class OpiniaoDto {

    private String username;
    private Integer nota;
    private String titulo;
    private String descricao;

    public OpiniaoDto(String username, Integer nota, String titulo, String descricao) {
        this.username = username;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getUsername() {
        return username;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
