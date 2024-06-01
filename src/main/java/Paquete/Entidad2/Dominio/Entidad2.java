package Paquete.Entidad2.Dominio;

import Paquete.Entidad1.Dominio.Entidad1;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Entidad2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @JsonBackReference
    @ManyToMany(mappedBy = "entidades2")
    private List<Entidad1> entidades1;
}
