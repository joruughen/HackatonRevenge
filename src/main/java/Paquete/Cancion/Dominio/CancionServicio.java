package Paquete.Cancion.Dominio;

import Paquete.Cancion.Infraestructura.RepositorioCancion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CancionServicio {
    @Autowired
    RepositorioCancion repositorioCancion;

    @Autowired
    ModelMapper mapper;

    public Page<Cancion> getAllCanciones(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Cancion> meditations = repositorioCancion.findAll(pageable);
        return meditations.map(cancion -> mapper.map(cancion, Cancion.class));
    }
}
