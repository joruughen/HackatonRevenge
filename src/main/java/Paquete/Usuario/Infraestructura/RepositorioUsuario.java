package Paquete.Usuario.Infraestructura;

import Paquete.Usuario.Dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCorreo(String correo);
}
