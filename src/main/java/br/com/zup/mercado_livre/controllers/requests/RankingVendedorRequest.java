package br.com.zup.mercado_livre.controllers.requests;

import javax.validation.constraints.*;

public class RankingVendedorRequest {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idVendedor;

    public RankingVendedorRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "RankingVendedor{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }

}
