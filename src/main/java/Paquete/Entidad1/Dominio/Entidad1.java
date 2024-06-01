package Paquete.Entidad1.Dominio;


import Paquete.Artista.Dominio.Artista;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Entidad1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "Entidad1_Entidad2",
            joinColumns = @JoinColumn(name = "Entidad1_id"),
            inverseJoinColumns = @JoinColumn(name = "Entidad2_id"))
    private List<Artista> entidades2;


}
