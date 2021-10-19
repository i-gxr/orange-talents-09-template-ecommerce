package br.com.zup.mercado_livre.services;

import br.com.zup.mercado_livre.models.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;

@Service
public class Ranking {

    public void process(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/rankings", request, void.class);
    }

}
