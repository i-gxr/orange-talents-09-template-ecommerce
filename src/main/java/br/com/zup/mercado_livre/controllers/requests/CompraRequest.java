package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.models.enums.*;
import org.springframework.http.converter.*;
import org.yaml.snakeyaml.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

public class CompraRequest {

    @NotNull
    @Positive
    @Min(1)
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    public CompraRequest(Integer quantidade, Gateway gateway) {
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(quantidade, gateway, produto, usuario);
    }

}
