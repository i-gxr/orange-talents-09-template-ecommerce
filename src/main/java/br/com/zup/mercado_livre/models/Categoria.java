package br.com.zup.mercado_livre.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

}
