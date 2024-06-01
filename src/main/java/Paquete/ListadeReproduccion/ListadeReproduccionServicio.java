package Paquete.ListadeReproduccion;

import Paquete.Cancion.Dominio.Cancion;
import Paquete.Correo.SendNewPlaylistMail;
import Paquete.Usuario.Dominio.Usuario;
import Paquete.Usuario.Infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListadeReproduccionServicio {

    @Autowired
    private ListadeReproduccionRepositorio listadeReproduccionRepositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

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
        applicationEventPublisher.publishEvent(new SendNewPlaylistMail(usuario, correo));
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
    public List<Cancion> getSongsInPlaylist(Integer playlistId) {
        ListadeReproduccion playlist = listadeReproduccionRepositorio.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist no encontrada"));
        return playlist.getCanciones();
    }

    public ListadeReproduccion addSongToPlaylist(Integer playlistId, Cancion cancion) {
        ListadeReproduccion playlist = listadeReproduccionRepositorio.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist no encontrada"));
        playlist.getCanciones().add(cancion);
        return listadeReproduccionRepositorio.save(playlist);
    }

    public void removeSongFromPlaylist(Integer playlistId, Integer songId) {
        ListadeReproduccion playlist = listadeReproduccionRepositorio.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist no encontrada"));
        playlist.getCanciones().removeIf(cancion -> cancion.getId().equals(songId));
        listadeReproduccionRepositorio.save(playlist);
    }
}