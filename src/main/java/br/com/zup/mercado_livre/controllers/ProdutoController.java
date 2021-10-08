package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;

@RestController
@RequestMapping("/products")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = request.toModel(entityManager, usuarioLogado);
        entityManager.persist(produto);
    }

}
