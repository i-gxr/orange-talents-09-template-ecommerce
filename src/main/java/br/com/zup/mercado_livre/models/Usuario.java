package br.com.zup.mercado_livre.models;

import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Deprecated
    public Usuario() {}

    public Usuario(String login, String senha) {
        this.login = login;
        encryptingPassword(senha);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(dataHoraCadastro, usuario.dataHoraCadastro) && Objects.equals(perfis, usuario.perfis);
    }

    public boolean correspondeProduto(Produto produto) {
        return this.id == produto.getUsuario().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, dataHoraCadastro, perfis);
    }

    private void encryptingPassword(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
