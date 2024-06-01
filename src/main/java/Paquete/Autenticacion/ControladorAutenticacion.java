package Paquete.Autenticacion;

import jakarta.validation.Valid;
import Paquete.Autenticacion.DTO.RespuestaJWT;
import Paquete.Autenticacion.DTO.SolicitudInicioSesion;
import Paquete.Autenticacion.DTO.SolicitudRegistro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class ControladorAutenticacion {

    private final ServicioAutenticacion servicioAutenticacion;

    @PostMapping("/register")
    public ResponseEntity<RespuestaJWT> registrar(@RequestBody @Valid SolicitudRegistro solicitud) {
        return ResponseEntity.ok(servicioAutenticacion.registrar(solicitud));
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaJWT> iniciarSesion(@RequestBody @Valid SolicitudInicioSesion solicitud) {
        return ResponseEntity.ok(servicioAutenticacion.iniciarSesion(solicitud));
    }
}
