package br.com.zup.mercado_livre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
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
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

}
