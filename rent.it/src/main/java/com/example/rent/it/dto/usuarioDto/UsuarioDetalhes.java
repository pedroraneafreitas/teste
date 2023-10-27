package com.example.rent.it.dto.usuarioDto;

import com.example.rent.it.object.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsuarioDetalhes implements UserDetails {
    private final String nome;

    private final String email;

    private final String password;

    public UsuarioDetalhes(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
