package br.com.zup.mercado_livre.repositories;

import br.com.zup.mercado_livre.models.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface OpiniaoRepository extends CrudRepository<Opiniao, Long> {

    List<Opiniao> findAllByProdutoId(Long id);

}
