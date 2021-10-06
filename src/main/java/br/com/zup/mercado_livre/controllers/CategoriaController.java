package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public void insert(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = request.toModel(entityManager);
        entityManager.persist(categoria);
    }

}
