package Paquete.Entidad1.Dominio;


import Paquete.Album.Dominio.Album;
import Paquete.Artista.Dominio.Artista;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    String titulo;

    @Column
    Integer duracion;


    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @JsonBackReference
    @ManyToMany(mappedBy = "canciones")
    private List<ListaDeReproduccion> entidades2;

}
