package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.models.*;

public interface PagamentoGateway {

    Transacao toTransacao(Compra compra);

}
