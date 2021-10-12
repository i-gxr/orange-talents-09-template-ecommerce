package br.com.zup.mercado_livre.controllers.responses;

import br.com.zup.mercado_livre.controllers.dto.*;
import br.com.zup.mercado_livre.models.*;

import javax.persistence.*;
import java.math.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public class ProdutoResponse {

    private String nome;
    private BigDecimal preco;
    private Integer qtdDisponivel;
    private Set<CaracteristicaDto> caracteristicas;
    private String descricao;
    private List<ImagemDto> imagens;
    private LocalDateTime dataHoraCadastro;
    private Double mediaAvaliacao;
    private Integer numTotalAvaliacoes;
    private List<OpiniaoDto> opinioes;
    private List<PerguntaDto> perguntas;
    private String usernameVendedor;

    @Deprecated
    public ProdutoResponse() {}

    public ProdutoResponse(Produto produto, EntityManager entityManager) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.qtdDisponivel = produto.getQtdDisponivel();
        this.caracteristicas = produto.getCaracteristicas().stream().map(c -> new CaracteristicaDto(c.getNome(), c.getDescricao())).collect(Collectors.toSet());
        this.descricao = produto.getDescricao();
        this.imagens = produto.getImagens(entityManager).stream().map(i -> new ImagemDto(i.getLink())).collect(Collectors.toList());
        this.dataHoraCadastro = produto.getDataHoraCadastro();
        this.mediaAvaliacao = produto.getMediaAvaliacao(entityManager);
        this.numTotalAvaliacoes = produto.getNumTotalAvaliacao(entityManager);
        this.opinioes = produto.getOpinioes(entityManager).stream().map(o -> new OpiniaoDto(o.getUsuario().getUsername(), o.getNota(), o.getTitulo(), o.getDescricao())).collect(Collectors.toList());
        this.perguntas = produto.getPerguntas(entityManager).stream().map(p -> new PerguntaDto(p.getUsuario().getUsername(), p.getTitulo())).collect(Collectors.toList());
        this.usernameVendedor = produto.getUsuario().getUsername();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQtdDisponivel() {
        return qtdDisponivel;
    }

    public Set<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<ImagemDto> getImagens() {
        return imagens;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public Integer getNumTotalAvaliacoes() {
        return numTotalAvaliacoes;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }

    public String getUsernameVendedor() {
        return usernameVendedor;
    }

}
