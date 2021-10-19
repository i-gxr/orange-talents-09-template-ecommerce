package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.models.enums.*;
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
                         + "\nRemetente: " + pergunta.getUsuario().getUsername() + "\n");
    }

    @Override
    public void sendEmail(Compra compra) {
        System.out.println("Nova mensagem para: " + compra.getProduto().getUsuario().getUsername()
                         + "\nIntenção de compra do produto " + compra.getProduto().getNome()
                         + "\nInteressado: " + compra.getUsuario().getUsername() + "\n");
    }

    @Override
    public void sendEmail(Transacao transacao) {
        String corpo = transacao.getStatusTransacao().equals(StatusTransacao.SUCESSO)
                ? "A compra do produto " + transacao.getCompra().getProduto().getNome() + " foi realizada com sucesso!"
                : "Não foi possível concluir a compra do produto " + transacao.getCompra().getProduto().getNome()
                + ".\nPor favor, tente novamente nesse link: payment/" + transacao.getCompra().getGateway().toString() + "/" + transacao.getCompra().getId();
        System.out.println(
                "E-mail para: " + transacao.getCompra().getUsuario().getUsername()
                + "\nMensagem: " + corpo + "\n");
    }

}
