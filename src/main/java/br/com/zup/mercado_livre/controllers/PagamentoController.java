package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.controllers.utils.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.models.enums.*;
import br.com.zup.mercado_livre.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/payments")
public class PagamentoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NotaFiscal notaFiscal;

    @Autowired
    private Ranking ranking;

    @Autowired
    private EmailSend emailSend;

    @PostMapping("/paypal/{idCompra}")
    @Transactional
    public void paymentPaypal(@PathVariable Long idCompra, @RequestBody @Valid PagamentoPaypalRequest request) {
        Compra compra = Optional.ofNullable(entityManager.find(Compra.class, idCompra)).orElseThrow(CompraNotFoundException::new);
        compra.addTransacao(request);
        entityManager.merge(compra);
        emailSend.sendEmail(request.toTransacao(compra));
        interactionOtherSystems(compra);
    }

    @PostMapping("/pagseguro/{idCompra}")
    @Transactional
    public void paymentPagseguro(@PathVariable Long idCompra, @RequestBody @Valid PagamentoPagseguroRequest request) {
        Compra compra = Optional.ofNullable(entityManager.find(Compra.class, idCompra)).orElseThrow(CompraNotFoundException::new);
        compra.addTransacao(request);
        entityManager.merge(compra);
        emailSend.sendEmail(request.toTransacao(compra));
        interactionOtherSystems(compra);
    }

    private void interactionOtherSystems(Compra compra) {
        if (compra.isSuccessfullyCompleted()) {
            notaFiscal.generate(compra);
            ranking.process(compra);
        }
    }

}
