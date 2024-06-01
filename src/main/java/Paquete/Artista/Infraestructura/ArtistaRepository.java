package Paquete.Artista.Infraestructura;

import Paquete.Artista.Dominio.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Artista findByNombre(String nombre);
}
