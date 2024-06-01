package Paquete.ListadeReproduccion;

import Paquete.Cancion.Dominio.Cancion;
import Paquete.Usuario.Dominio.Usuario;
import Paquete.Usuario.Infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListadeReproduccionServicio {

    @Autowired
    private ListadeReproduccionRepositorio listadeReproduccionRepositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public List<ListadeReproduccion> getPlaylistsByUserCorreo(String correo) {
        Usuario usuario = repositorioUsuario.findByCorreo(correo).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return listadeReproduccionRepositorio.findAllByIdUser(usuario.getId());
    }

    public Optional<ListadeReproduccion> getPlaylistById(Integer playlistId) {
        return listadeReproduccionRepositorio.findById(playlistId);
    }

    public ListadeReproduccion createPlaylist(String correo, ListadeReproduccion playlist) {
        Usuario usuario = repositorioUsuario.findByCorreo(correo).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        playlist.setIdUser(usuario.getId());
        return listadeReproduccionRepositorio.save(playlist);
    }

    public ListadeReproduccion updatePlaylist(Integer playlistId, ListadeReproduccion playlistDetails) {
        ListadeReproduccion playlist = listadeReproduccionRepositorio.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist no encontrada"));
        playlist.setNombre(playlistDetails.getNombre());
        return listadeReproduccionRepositorio.save(playlist);
    }

    public void deletePlaylist(Integer playlistId) {
        listadeReproduccionRepositorio.deleteById(playlistId);
    }
}