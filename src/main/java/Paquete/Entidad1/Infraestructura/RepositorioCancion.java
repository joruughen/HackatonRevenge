package Paquete.Entidad1.Infraestructura;

import Paquete.Entidad1.Dominio.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCancion extends JpaRepository<Cancion, Integer> {

}
