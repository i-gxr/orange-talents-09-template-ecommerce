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
    public Set<ImagemProduto> uploadImage(ImagemProdutoRequest request, Produto produto) {
        return request.getImagens().stream().map(i -> new ImagemProduto(imageToBase64(i), produto)).collect(Collectors.toSet());
    }

    private String imageToBase64(MultipartFile image) {
        if (image.getContentType() == null)
            throw new ImagemNotValidException();

        if (!Arrays.asList("image/jpeg", "image/png", "image/gif").contains(image.getContentType().toString()))
            throw new ImagemNotValidException();

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(image.getBytes(), false)));
            return sb.toString();
        }
            catch (IOException e) {
                throw new ImagemNotValidException();
            }
    }

}
