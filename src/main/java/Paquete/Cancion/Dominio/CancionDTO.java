package Paquete.Cancion.Dominio;

import Paquete.Album.Dominio.Album;
import Paquete.Artista.Dominio.Artista;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CancionDTO {

    String titulo;

    Integer duracion;

    Artista artista;

    Album album;
}