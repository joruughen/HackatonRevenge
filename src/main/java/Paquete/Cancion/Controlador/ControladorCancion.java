package Paquete.Cancion.Controlador;

import Paquete.Cancion.Dominio.Cancion;
import Paquete.Cancion.Dominio.CancionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cancion")
public class ControladorCancion {
    @Autowired
    CancionServicio cancionServicio;

    @GetMapping("")
    public ResponseEntity<Page<Cancion>> getAllMeditations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Cancion> meditations = cancionServicio.getAllCanciones(page, size);
        return ResponseEntity.ok(meditations);
    }

}
