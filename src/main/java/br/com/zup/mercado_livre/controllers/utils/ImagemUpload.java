package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;

import java.util.*;

public interface ImagemUpload {

    Set<Imagem> uploadImage(ImagemRequest request, Produto produto);

}
