package br.com.zup.mercado_livre.models;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import org.apache.tomcat.util.codec.binary.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.*;

import javax.imageio.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "tb_imagem_produto")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotBlank
    @Column(nullable = false)
    private String link;

    @ManyToOne
    private Produto produto;

    public Imagem(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

}
