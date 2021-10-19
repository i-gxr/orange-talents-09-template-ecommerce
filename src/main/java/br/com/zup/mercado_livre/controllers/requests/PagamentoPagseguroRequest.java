package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.utils.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.models.enums.*;

import javax.persistence.*;
import javax.validation.constraints.*;

public class PagamentoPagseguroRequest implements PagamentoGateway {

    @NotNull
    private Long idPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PagseguroStatusCompra statusCompra;

    public PagamentoPagseguroRequest(Long idPagamento, PagseguroStatusCompra statusCompra) {
        this.idPagamento = idPagamento;
        this.statusCompra = statusCompra;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(this.idPagamento, statusCompra.equals(PagseguroStatusCompra.SUCESSO) ? StatusTransacao.SUCESSO : StatusTransacao.ERRO, compra);
    }
}
