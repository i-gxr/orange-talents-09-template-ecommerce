package br.com.zup.mercado_livre.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String login;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 6)
    private String senha;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    public Usuario(String login, String senha) {
        this.login = login;
        encryptingPassword(senha);
    }

    private void encryptingPassword(String senha) {
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }

}
