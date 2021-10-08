package br.com.zup.mercado_livre.controllers;

import br.com.zup.mercado_livre.configs.security.*;
import br.com.zup.mercado_livre.controllers.dto.*;
import br.com.zup.mercado_livre.controllers.requests.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.toUsernamePasswordAuthenticationToken();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }
            catch (AuthenticationException e) {
                return ResponseEntity.badRequest().build();
            }
    }

}
