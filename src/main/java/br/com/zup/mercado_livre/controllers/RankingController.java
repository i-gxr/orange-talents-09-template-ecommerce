package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.requests.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/rankings")
public class RankingController {

    @PostMapping
    public void create(@RequestBody @Valid RankingVendedorRequest request) {
        System.out.println("Ranking vendedor: " + request);
    }

}
