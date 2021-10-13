package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@Primary
public class DevEmailSender implements EmailSend {

    @Override
    public void sendEmail(Pergunta pergunta) {
        System.out.println("Nova pergunta para o vendedor de e-mail: " + pergunta.getProduto().getUsuario().getUsername()
                         + "\nProduto: " + pergunta.getProduto().getNome()
                         + "\nPergunta: " + pergunta.getTitulo()
                         + "\nRemetente: " + pergunta.getUsuario().getUsername());
    }

    @Override
    public void sendEmail(Compra compra) {
        System.out.println("Nova mensagem para: " + compra.getProduto().getUsuario().getUsername()
                         + "\nIntenção de compra do produto " + compra.getProduto().getNome()
                         + "\nInteressado: " + compra.getUsuario().getUsername());
    }

}
