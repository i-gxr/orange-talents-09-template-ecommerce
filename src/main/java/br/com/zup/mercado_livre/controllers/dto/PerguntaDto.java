package br.com.zup.mercado_livre.controllers.dto;

public class PerguntaDto {

    private String username;
    private String titulo;

    public PerguntaDto(String username, String titulo) {
        this.username = username;
        this.titulo = titulo;
    }

    public String getUsername() {
        return username;
    }

    public String getTitulo() {
        return titulo;
    }
}
