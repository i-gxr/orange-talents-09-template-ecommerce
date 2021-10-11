package br.com.zup.mercado_livre.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    @Column(nullable = false)
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer qtdDisponivel;

    @NotBlank
    @Size(max = 1000)
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> caracteristicas;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    @Deprecated
    public Produto() {}

    public Produto(String nome, BigDecimal valor, Integer qtdDisponivel, String descricao, Set<Caracteristica> caracteristicas, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
