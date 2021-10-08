package br.com.zup.mercado_livre.repositories;

import br.com.zup.mercado_livre.models.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String username);

}
