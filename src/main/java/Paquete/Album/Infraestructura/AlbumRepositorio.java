package Paquete.Album.Infraestructura;

import Paquete.Album.Dominio.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepositorio extends JpaRepository<Album, Integer>{
}
