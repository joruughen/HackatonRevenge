package Paquete.Artista.Dominio;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer idArtist;

    @Column
    String nombre;

}
