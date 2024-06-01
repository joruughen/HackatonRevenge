package Paquete.ListadeReproduccion;

import Paquete.Entidad1.Dominio.Cancion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListadeReproduccionServicio {

    @Autowired
    private ListadeReproduccionRepositorio repositorio;

    public List<ListadeReproduccion> obtenerTodasLasListasDeUsuario(int userId) {
        return repositorio.findAllByUserId(userId);
    }

    public Optional<ListadeReproduccion> obtenerListaPorId(int id) {
        return repositorio.findById(id);
    }

    public ListadeReproduccion guardarLista(int userId, ListadeReproduccion lista) {
        lista.setIdUser(userId);
        return repositorio.save(lista);
    }

    public void eliminarLista(int id) {
        repositorio.deleteById(id);
    }

    public ListadeReproduccion agregarCancionALista(int id, Cancion cancion) {
        Optional<ListadeReproduccion> listaOpt = repositorio.findById(id);
        if (listaOpt.isPresent()) {
            ListadeReproduccion lista = listaOpt.get();
            lista.getCanciones().add(cancion);
            return repositorio.save(lista);
        } else {
            throw new RuntimeException("Lista de reproducción no encontrada");
        }
    }
}