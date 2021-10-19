package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.utils.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.models.enums.*;

import javax.validation.constraints.*;

public class PagamentoPaypalRequest implements PagamentoGateway {

    @NotNull
    private Long idPagamento;

    @Min(0)
    @Max(1)
    private Integer statusCompra;

    public PagamentoPaypalRequest(Long idPagamento, Integer statusCompra) {
        this.idPagamento = idPagamento;
        this.statusCompra = statusCompra;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(this.idPagamento, statusCompra != 0 ? StatusTransacao.SUCESSO : StatusTransacao.ERRO, compra);
    }

}
