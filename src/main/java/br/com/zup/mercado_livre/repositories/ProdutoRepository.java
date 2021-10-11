package br.com.zup.mercado_livre.repositories;

import br.com.zup.mercado_livre.models.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
