package br.com.zup.mercado_livre.models;

import org.springframework.security.core.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_perfil")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Deprecated
    public Perfil() {}

    public Perfil(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
