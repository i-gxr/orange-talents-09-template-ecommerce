package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.validations.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.security.core.parameters.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

public class ProdutoRequest {

    @NotBlank
    @Size(min = 3)
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal valor;

    @PositiveOrZero
    private Integer qtdDisponivel;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @Size(min = 3)
    private Set<CaracteristicaRequest> caracteristicas;

    @ExistingId(domainClass = Categoria.class, fieldName = "id")
    @NotNull
    private Long categoriaId;

    public ProdutoRequest(String nome, BigDecimal valor, Integer qtdDisponivel, String descricao, Set<CaracteristicaRequest> caracteristicas, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(EntityManager em, Usuario usuarioLogado) {
        Categoria categoria = em.find(Categoria.class, categoriaId);
        return new Produto(nome, valor, qtdDisponivel, descricao,
                caracteristicas.stream().map(CaracteristicaRequest::toModel).collect(Collectors.toSet()), categoria, usuarioLogado);
    }

}
