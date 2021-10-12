package br.com.zup.mercado_livre.repositories;

import br.com.zup.mercado_livre.models.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface ImagemRepository extends CrudRepository<Imagem, Long> {

    List<Imagem> findAllByProdutoId(Long id);

}
