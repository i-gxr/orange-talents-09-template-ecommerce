package br.com.zup.mercado_livre.models;

import br.com.zup.mercado_livre.models.enums.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "tb_transacao_pagamento")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long idPagamento;

    @Column(nullable = false)
    private LocalDateTime dataHoraTransacao = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTransacao;

    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {}

    public Transacao(Long idPagamento, StatusTransacao statusTransacao, Compra compra) {
        this.idPagamento = idPagamento;
        this.statusTransacao = statusTransacao;
        this.compra = compra;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public Compra getCompra() {
        return compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idPagamento, transacao.idPagamento) && Objects.equals(compra, transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagamento, compra);
    }
}
