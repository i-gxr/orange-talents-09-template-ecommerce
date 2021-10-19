package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.requests.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

    @PostMapping
    public void create(@RequestBody @Valid NotaFiscalRequest request) {
        System.out.println("Criando nota fiscal: " + request);
    }

}
