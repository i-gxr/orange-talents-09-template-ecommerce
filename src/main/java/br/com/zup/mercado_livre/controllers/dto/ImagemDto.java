package br.com.zup.mercado_livre.controllers.dto;

public class ImagemDto {

    private String link;

    public ImagemDto(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
