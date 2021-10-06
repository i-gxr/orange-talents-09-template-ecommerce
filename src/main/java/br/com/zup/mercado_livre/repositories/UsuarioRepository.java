package br.com.zup.mercado_livre.repositories;

import br.com.zup.mercado_livre.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
