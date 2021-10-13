package br.com.zup.mercado_livre.controllers.exceptions;

public class GatewayPagamentoNotFoundException extends BussinessException {

    public GatewayPagamentoNotFoundException() {
        super("O gateway de pagamento informado não existe!");
    }

}
