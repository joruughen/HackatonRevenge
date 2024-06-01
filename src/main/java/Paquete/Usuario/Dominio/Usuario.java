package Paquete.Usuario.Dominio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(nullable = false)
    Rol rol;

    @Column(nullable = false)
    String nombre;

    @Column(nullable = false, unique = true)
    String correo;

    @Column(nullable = false)
    String contrasena;

    @Column(nullable = false)
    LocalDateTime creadoAlas;
    LocalDateTime actualizadoAlas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.getRol().name()));
    }

    @Override
    public String getPassword() {
        return this.getContrasena();
    }

    @Override
    public String getUsername() {
        return this.getCorreo();
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