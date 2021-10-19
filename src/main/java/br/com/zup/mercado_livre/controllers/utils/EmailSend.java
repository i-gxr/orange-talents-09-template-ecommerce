package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.models.*;

public interface EmailSend {

    void sendEmail(Pergunta pergunta);

    void sendEmail(Compra compra);

    void sendEmail(Transacao transacao);

}
