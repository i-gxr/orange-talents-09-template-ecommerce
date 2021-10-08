package br.com.zup.mercado_livre.configs.security;

import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.repositories.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository repository;

    public AuthenticationByTokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean tokenIsValid = tokenService.isTokenValido(token);
        if (tokenIsValid)
            authenticateUsuario(token);
        filterChain.doFilter(request, response);
    }

    private void authenticateUsuario(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = repository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
            return null;
        return token.substring(7, token.length());
    }
}
