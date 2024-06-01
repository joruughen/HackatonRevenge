package Paquete.Cancion.Infraestructura;

import Paquete.Cancion.Dominio.Cancion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCancion extends JpaRepository<Cancion, Integer> {
    Page<Cancion> findAll(Pageable pageable);
}
