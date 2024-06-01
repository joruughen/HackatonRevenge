package Paquete.Entidad1.Dominio;

import Paquete.Entidad1.Infraestructura.RepositorioCancion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionServicio {
    @Autowired
    RepositorioCancion repositorioCancion;
}
