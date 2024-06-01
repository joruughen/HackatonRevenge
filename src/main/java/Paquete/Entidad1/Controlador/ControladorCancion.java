package Paquete.Entidad1.Controlador;

import Paquete.Entidad1.Dominio.CancionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cancion")
public class ControladorCancion {
    @Autowired
    CancionServicio cancionServicio;

}
