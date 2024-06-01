package Paquete.ListadeReproduccion;

import Paquete.Cancion.Dominio.Cancion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class ListadeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPlaylist;

    private String nombre;

    private Integer idUser;

    private Date fechaDeCreacion;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "ListadeReproduccion_Cancion",
            joinColumns = @JoinColumn(name = "ListadeReproduccion_id"),
            inverseJoinColumns = @JoinColumn(name = "Cancion_id"))
    private List<Cancion> canciones;
}
