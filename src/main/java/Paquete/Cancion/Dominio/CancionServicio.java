package Paquete.Cancion.Dominio;

import Paquete.Cancion.Infraestructura.RepositorioCancion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    public Cancion createCancion(CancionDTO cancionDto) {
        Cancion cancion = mapper.map(cancionDto, Cancion.class);

//        cancion.setStudent(student);
        repositorioCancion.save(cancion);
        return cancion;
    }


    public Cancion updateCancion(CancionDTO cancionDTO, Integer id) {
        Optional<Cancion> cancion = repositorioCancion.findById(id);

        if (cancion.isPresent()){
            cancion.get().setArtista(cancionDTO.getArtista());
            cancion.get().setDuracion(cancionDTO.getDuracion());
            cancion.get().setAlbum(cancionDTO.getAlbum());
            cancion.get().setTitulo(cancionDTO.getTitulo());
            return cancion.get();
        }
        return new Cancion();
    }


    public Cancion deleteCancion(Integer id) {

        Optional<Cancion> cancion = repositorioCancion.findById(id);

        if (cancion.isPresent()) {
            Cancion cancionToDelete = cancion.get();
            repositorioCancion.delete(cancionToDelete);
            return mapper.map(cancionToDelete,Cancion.class);
        }else {
            return new Cancion();
        }
    }
}