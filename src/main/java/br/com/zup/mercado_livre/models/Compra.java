package br.com.zup.mercado_livre.models;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import br.com.zup.mercado_livre.controllers.utils.*;
import br.com.zup.mercado_livre.models.enums.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

@Entity
@Table(name = "tb_compra_produto")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Min(1)
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gateway gateway;

    @Column(nullable = false)
    private BigDecimal valorProdutoMomento;

    @Enumerated
    @Column(nullable = false)
    private StatusCompra status;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(cascade=CascadeType.MERGE)
    private Set<Transacao> transacoes;

    @Deprecated
    public Compra() {}

    public Compra(Integer quantidade, Gateway gateway, Produto produto, Usuario usuario) {
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.status = StatusCompra.INICIADA;
        this.produto = produto;
        this.usuario = usuario;
        this.valorProdutoMomento = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Gateway getGateway() {
        return gateway;
    }

    private void changeStatusCompleted() {
        if (isSuccessfullyCompleted())
            this.status = StatusCompra.FINALIZADA;
    }

    public void addTransacao(PagamentoGateway request) {
        Set<Transacao> transacoes = this.transacoes.stream().filter(t -> t.getStatusTransacao().equals(StatusTransacao.SUCESSO)).collect(Collectors.toSet());
        if (!transacoes.isEmpty())
            throw new TransacaoConcluidaException();

        if (this.transacoes.contains(request.toTransacao(this)))
            throw new TransacaoJaExistenteException();

        this.transacoes.add(request.toTransacao(this));
        changeStatusCompleted();
    }

    public boolean isSuccessfullyCompleted() {
        Set<Transacao> transacoes = this.transacoes.stream().filter(t -> t.getStatusTransacao().equals(StatusTransacao.SUCESSO)).collect(Collectors.toSet());
        return !transacoes.isEmpty();
    }
}
