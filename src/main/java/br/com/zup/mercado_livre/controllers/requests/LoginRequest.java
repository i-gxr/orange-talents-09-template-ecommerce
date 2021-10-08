package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.controllers.validations.*;
import br.com.zup.mercado_livre.models.*;
import org.springframework.security.authentication.*;

import javax.validation.constraints.*;

public class LoginRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public LoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }

}
