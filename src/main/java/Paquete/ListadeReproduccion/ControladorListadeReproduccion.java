package Paquete.ListadeReproduccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorListadeReproduccion {

    @Autowired
    private ListadeReproduccionServicio servicio;

    @GetMapping("/users/{user_id}/playlists")
    public List<ListadeReproduccion> obtenerTodasLasListasDeUsuario(@PathVariable int user_id) {
        return servicio.obtenerTodasLasListasDeUsuario(user_id);
    }

    @GetMapping("/playlists/{id}")
    public ResponseEntity<ListadeReproduccion> obtenerListaPorId(@PathVariable int id) {
        return servicio.obtenerListaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users/{user_id}/playlists")
    public ListadeReproduccion guardarLista(@PathVariable int user_id, @RequestBody ListadeReproduccion lista) {
        return servicio.guardarLista(user_id, lista);
    }

   // @PutMapping("/playlists/{id}")
    //public ResponseEntity<ListadeReproduccion> actualizarLista(@PathVariable int id, @RequestBody ListadeReproduccion lista) {
      //  return servicio.obtenerListaPorId(id)
        //        .map(listaExistente -> {
          //          lista.setIdPlaylist(id);
            //        return ResponseEntity.ok(servicio.guardarLista(user_id, lista));
              //  })
                //.orElse(ResponseEntity.notFound().build());
   // }

    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<Void> eliminarLista(@PathVariable int id) {
        if (servicio.obtenerListaPorId(id).isPresent()) {
            servicio.eliminarLista(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
