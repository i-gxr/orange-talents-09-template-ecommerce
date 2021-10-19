package br.com.zup.mercado_livre.services;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;

@Service
public class NotaFiscal {

    public void generate(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, void.class);
    }

}
