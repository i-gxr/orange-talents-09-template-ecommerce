package br.com.zup.mercado_livre.controllers.utils;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import br.com.zup.mercado_livre.controllers.requests.*;
import br.com.zup.mercado_livre.models.*;
import org.apache.tomcat.util.codec.binary.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

@Component
@Primary
public class DevImagemUploader implements ImagemUpload {

    @Override
    public Set<Imagem> uploadImage(ImagemRequest request, Produto produto) {
        return request.getImagens().stream().map(i -> new Imagem(imageGeneratedLink(i), produto)).collect(Collectors.toSet());
    }

    private String imageGeneratedLink(MultipartFile image) {
        return "https://zup.images/" + image.getOriginalFilename();
    }

}
