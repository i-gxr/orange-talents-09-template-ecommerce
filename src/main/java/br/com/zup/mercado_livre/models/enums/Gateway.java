package br.com.zup.mercado_livre.models.enums;

import java.net.*;

public enum Gateway {

    PAGSEGURO {
        @Override
        public String getLink(Long idCompra, String url) {
            return "pagseguro.com?returnId=" + idCompra + "&redirectUrl=" + url;
        }
    },

    PAYPAL {
        @Override
        public String getLink(Long idCompra, String url) {
            return "paypal.com?buyerId=" + idCompra + "&redirectUrl=" + url;
        }
    };

    public abstract String getLink(Long idCompra, String url);

}
