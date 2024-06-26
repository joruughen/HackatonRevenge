package Paquete.ListadeReproduccion;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListadeReproduccionRepositorio extends JpaRepository<ListadeReproduccion, Integer> {
    List<ListadeReproduccion> findAllByIdUser(Integer idUser);
}