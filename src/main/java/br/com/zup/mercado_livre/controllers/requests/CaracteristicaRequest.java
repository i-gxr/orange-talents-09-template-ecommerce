package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.models.*;

import javax.validation.constraints.*;
import java.util.*;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaRequest that = (CaracteristicaRequest) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

    public Caracteristica toModel() {
        return new Caracteristica(this.nome, this.descricao);
    }

}
