package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.controllers.requests.UsuarioRequest;
import br.com.zup.mercado_livre.model.Usuario;
import br.com.zup.mercado_livre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        repository.save(usuario);
    }

}
