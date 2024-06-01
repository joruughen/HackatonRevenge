package Paquete.Usuario.Controlador;

import Paquete.Usuario.Dominio.ServicioUsuario;
import Paquete.Usuario.DTO.UsuarioDTO;
import Paquete.Usuario.Dominio.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class ControladorUsuario {

    private final ServicioUsuario servicioUsuario;

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @GetMapping("/todos")
    public ResponseEntity<Page<Usuario>> obtenerTodos(@RequestParam int pagina, @RequestParam int tamano) {
        return ResponseEntity.ok(servicioUsuario.obtenerTodosUsuarios(pagina, tamano));
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/yo")
    public ResponseEntity<UsuarioDTO> obtenerMiUsuario() {
        return ResponseEntity.ok(servicioUsuario.obtenerUsuarioActual());
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/contar")
    public ResponseEntity<Long> contarUsuarios() {
        return ResponseEntity.ok(servicioUsuario.contarUsuarios());
    }
}
