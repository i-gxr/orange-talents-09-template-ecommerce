package br.com.zup.mercado_livre.models.enums;

import java.net.*;

public enum Gateway {

    PAGSEGURO {
        @Override
        public URI getLink(Long idCompra, String url) {
            return URI.create("http://pagseguro.com?returnId=" + idCompra + "&redirectUrl=" + url);
        }
    },

    PAYPAL {
        @Override
        public URI getLink(Long idCompra, String url) {
            return URI.create("http://paypal.com?buyerId=" + idCompra + "&redirectUrl=" + url);
        }
    };

    public abstract URI getLink(Long idCompra, String url);

}
