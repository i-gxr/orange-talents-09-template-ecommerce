package br.com.zup.mercado_livre.controllers.dto;

import java.util.*;

public class CaracteristicaDto {

    private String nome;
    private String descricao;

    public CaracteristicaDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaDto that = (CaracteristicaDto) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

}
