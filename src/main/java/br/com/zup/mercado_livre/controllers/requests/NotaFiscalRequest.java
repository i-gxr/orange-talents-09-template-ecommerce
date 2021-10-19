package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.validations.*;
import br.com.zup.mercado_livre.models.*;

import javax.validation.constraints.*;

public class NotaFiscalRequest {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idComprador;

    public NotaFiscalRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
