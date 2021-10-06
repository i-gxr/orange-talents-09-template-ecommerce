package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.validations.*;
import br.com.zup.mercado_livre.models.*;

import javax.persistence.*;
import javax.validation.constraints.*;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @IfNotNullExistingId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public CategoriaRequest(String nome, Long categoriaId) {
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public Categoria toModel(EntityManager em) {
        if (categoriaId != null)
            return new Categoria(nome, em.find(Categoria.class, categoriaId));
        return new Categoria(nome);
    }

}
