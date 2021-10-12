package br.com.zup.mercado_livre.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_opiniao_produto")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer nota;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @Deprecated
    public Opiniao() {}

    public Opiniao(Integer nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
