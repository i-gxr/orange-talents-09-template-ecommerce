package br.com.zup.mercado_livre.models;

import br.com.zup.mercado_livre.models.enums.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;

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
}
