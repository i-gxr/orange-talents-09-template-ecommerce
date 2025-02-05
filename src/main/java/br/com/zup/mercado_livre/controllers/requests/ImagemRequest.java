package br.com.zup.mercado_livre.controllers.requests;

import br.com.zup.mercado_livre.models.*;
import org.springframework.web.multipart.*;

import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.*;

public class ImagemRequest {

    @Size(min = 1)
    @NotNull
    private Set<MultipartFile> imagens;

    public ImagemRequest(Set<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public Set<MultipartFile> getImagens() {
        return imagens;
    }

}
