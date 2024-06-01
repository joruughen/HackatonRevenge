package Paquete.ListadeReproduccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ControladorListadeReproduccion {

    @Autowired
    private ListadeReproduccionServicio listadeReproduccionServicio;

    @GetMapping("/users/{userId}/playlists")
    public ResponseEntity<List<ListadeReproduccion>> getPlaylistsByUserId(@PathVariable String correo) {
        List<ListadeReproduccion> playlists = listadeReproduccionServicio.getPlaylistsByUserCorreo(correo);
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<ListadeReproduccion> getPlaylistById(@PathVariable Integer playlistId) {
        return listadeReproduccionServicio.getPlaylistById(playlistId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users/{userId}/playlists")
    public ResponseEntity<ListadeReproduccion> createPlaylist(@PathVariable String userId, @RequestBody ListadeReproduccion playlist) {
        ListadeReproduccion createdPlaylist = listadeReproduccionServicio.createPlaylist(userId, playlist);
        return ResponseEntity.ok(createdPlaylist);
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<ListadeReproduccion> updatePlaylist(@PathVariable Integer playlistId, @RequestBody ListadeReproduccion playlistDetails) {
        ListadeReproduccion updatedPlaylist = listadeReproduccionServicio.updatePlaylist(playlistId, playlistDetails);
        return ResponseEntity.ok(updatedPlaylist);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer playlistId) {
        listadeReproduccionServicio.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}