package br.com.zup.mercado_livre.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

@Entity
@Table(name = "tb_pergunta_produto")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
