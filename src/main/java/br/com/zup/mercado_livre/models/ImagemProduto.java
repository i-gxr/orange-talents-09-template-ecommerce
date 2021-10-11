package br.com.zup.mercado_livre.models;

import br.com.zup.mercado_livre.controllers.exceptions.*;
import org.apache.tomcat.util.codec.binary.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.*;

import javax.imageio.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "tb_imagem_produto")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotBlank
    @Column(nullable = false)
    private String base64;

    @ManyToOne
    private Produto produto;

    public ImagemProduto(@NotNull MultipartFile image, Produto produto) {
        imageToBase64(image);
        this.produto = produto;
    }

    private void imageToBase64(MultipartFile image) {
        if (!Arrays.asList("image/jpeg", "image/png", "image/gif").contains(image.getContentType().toString()))
            throw new ImagemNotValidException();

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(image.getBytes(), false)));
            this.base64 = sb.toString();
        }
            catch (IOException e) {
                throw new ImagemNotValidException();
            }
    }

}
