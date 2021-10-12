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

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQtdDisponivel() {
        return qtdDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return new HashSet<Caracteristica>(this.caracteristicas);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Imagem> getImagens(EntityManager entityManager) {
        List<Imagem> imagens = entityManager.createQuery("SELECT i FROM Imagem i WHERE i.produto.id = :id", Imagem.class)
                .setParameter("id", this.id).getResultList();
        return imagens;
    }

    public List<Opiniao> getOpinioes(EntityManager entityManager) {
        List<Opiniao> opinioes = entityManager.createQuery("SELECT o FROM Opiniao o WHERE o.produto.id = :id", Opiniao.class)
                .setParameter("id", this.id).getResultList();
        return opinioes;
    }

    public List<Pergunta> getPerguntas(EntityManager entityManager) {
        List<Pergunta> perguntas = entityManager.createQuery("SELECT p FROM Pergunta p WHERE p.produto.id = :id", Pergunta.class)
                .setParameter("id", this.id).getResultList();
        return perguntas;
    }

    public Double getMediaAvaliacao(EntityManager entityManager) {
        return getOpinioes(entityManager).stream().mapToDouble(Opiniao::getNota).average().orElse(0);
    }

    public Integer getNumTotalAvaliacao(EntityManager entityManager) {
        return getOpinioes(entityManager).size();
    }

}
