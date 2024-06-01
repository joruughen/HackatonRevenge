package Paquete.Cancion.Controlador;

import Paquete.Cancion.Dominio.Cancion;
import Paquete.Cancion.Dominio.CancionDTO;
import Paquete.Cancion.Dominio.CancionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canciones")
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

    @PostMapping("")
    public ResponseEntity<Cancion> createCancion(@RequestBody CancionDTO cancionDto) {
        Cancion cancion = cancionServicio.createCancion(cancionDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location","study_session/" + cancion.getId()).
                body(cancion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> updateCancion(@RequestBody CancionDTO cancionDTO, @PathVariable Integer id) {
        Cancion cancion = cancionServicio.updateCancion(cancionDTO, id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Location","canciones/" + cancion.getId()).
                body(cancion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cancion> deleteCancion(@PathVariable Integer id){
        Cancion cancion = cancionServicio.deleteCancion(id);
        if (cancion.getId() == null){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}