package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@Primary
public class DevEmailSender implements EmailSend {

    @Override
    public Pergunta sendEmail(PerguntaRequest request, Produto produto, Usuario usuario) {
        Pergunta pergunta = request.toModel(produto, usuario);
        System.out.println("Nova pergunta para o vendedor de e-mail: " + produto.getUsuario().getUsername()
                         + "\nPergunta: " + request.getTitulo()
                         + "\nRemetente: " + usuario.getUsername());
        return pergunta;
    }

}
