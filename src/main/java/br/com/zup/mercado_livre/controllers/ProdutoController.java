package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/products")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = request.toModel(entityManager, usuarioLogado);
        entityManager.persist(produto);
    }

    @PostMapping("/{id}/images")
    @Transactional
    public void uploadImages(@PathVariable Long id, @RequestParam(required = true) MultipartFile[] imagens, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = repository.findById(id).orElseThrow(ProdutoNotFoundException::new);
        if (!usuarioLogado.correspondeProduto(produto))
            throw new ProdutoForbiddenException();
        if (imagens[0].isEmpty())
            throw new ImagemNotValidException();

        List<ImagemProduto> imagensProduto = Arrays.stream(imagens).map(i -> new ImagemProduto(i, produto)).collect(Collectors.toList());
        imagensProduto.stream().forEach(i -> entityManager.persist(i));
    }

}
