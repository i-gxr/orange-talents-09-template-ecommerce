package br.com.zup.mercado_livre.repositories;

import br.com.zup.mercado_livre.models.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface PerguntaRepository extends CrudRepository<Pergunta, Long> {

    List<Pergunta> findAllByProdutoId(Long id);

}
