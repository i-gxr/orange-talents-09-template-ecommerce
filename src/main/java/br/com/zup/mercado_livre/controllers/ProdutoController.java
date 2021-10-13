package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.dto.*;
import br.com.zup.mercado_livre.controllers.exceptions.*;
import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.controllers.responses.*;
import br.com.zup.mercado_livre.controllers.utils.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.models.enums.*;
import br.com.zup.mercado_livre.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/products")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ImagemUpload imagemUpload;

    @Autowired
    private EmailSend emailSend;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = request.toModel(entityManager, usuarioLogado);
        entityManager.persist(produto);
    }

    @PostMapping("/{id}/images")
    @Transactional
    public void uploadImages(@PathVariable Long id, @Valid ImagemRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = repository.findById(id).orElseThrow(ProdutoNotFoundException::new);
        if (!usuarioLogado.correspondeProduto(produto))
            throw new ProdutoForbiddenException();

        Set<Imagem> imagensProduto = imagemUpload.uploadImage(request, produto);
        imagensProduto.stream().forEach(i -> entityManager.persist(i));
    }

    @PostMapping("/{id}/opinions")
    @Transactional
    public void addOpinions(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = repository.findById(id).orElseThrow(ProdutoNotFoundException::new);
        Opiniao opiniao = request.toModel(produto, usuarioLogado);
        entityManager.persist(opiniao);
    }

    @PostMapping("/{id}/questions")
    @Transactional
    public void addQuestion(@PathVariable Long id, @RequestBody @Valid PerguntaRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = repository.findById(id).orElseThrow(ProdutoNotFoundException::new);
        Pergunta pergunta = request.toModel(produto, usuarioLogado);
        entityManager.persist(pergunta);
        emailSend.sendEmail(pergunta);
    }

    @GetMapping("/{id}")
    @Transactional
    public ProdutoResponse findProduct(@PathVariable Long id) {
        Produto produto = repository.findById(id).orElseThrow(ProdutoNotFoundException::new);
        ProdutoResponse response = new ProdutoResponse(produto, entityManager);
        return response;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @PostMapping("/{id}/buy")
    @Transactional
    public URI buyProduct(@PathVariable Long id, @RequestBody @Valid CompraRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = repository.findById(id).orElseThrow(ProdutoNotFoundException::new);
        Compra compra = request.toModel(produto, usuarioLogado);
        produto.abateEstoque(request.getQuantidade());
        entityManager.persist(compra);
        entityManager.persist(produto);
        emailSend.sendEmail(compra);
        return compra.getGateway().getLink(compra.getId(), "https://mercadolivre.com.br");
    }

}
