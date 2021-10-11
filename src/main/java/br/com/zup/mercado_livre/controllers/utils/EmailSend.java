package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;

public interface EmailSend {

    Pergunta sendEmail(PerguntaRequest request, Produto produto, Usuario usuario);

}
