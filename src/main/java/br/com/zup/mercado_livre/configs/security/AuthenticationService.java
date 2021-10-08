package br.com.zup.mercado_livre.configs.security;

import br.com.zup.mercado_livre.models.*;
import br.com.zup.mercado_livre.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLogin(username);
        if (usuario.isPresent())
            return usuario.get();
        throw new UsernameNotFoundException("Credenciais não encontradas!");
    }

}
