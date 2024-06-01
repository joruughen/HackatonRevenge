package Paquete.Usuario.Dominio;

import Paquete.Usuario.DTO.UsuarioDTO;
import Paquete.Usuario.Infraestructura.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final ModelMapper modelMapper;
    private static final String USUARIO_NO_ENCONTRADO = "No se encontró el usuario.";

    public UserDetailsService servicioDetallesUsuario() {
        return correo -> repositorioUsuario
                .findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException(USUARIO_NO_ENCONTRADO));
    }

    public Page<Usuario> obtenerTodosUsuarios(int pagina, int tamano) {
        Pageable paginable = PageRequest.of(pagina, tamano);
        return repositorioUsuario.findAll(paginable);
    }

    public UsuarioDTO obtenerUsuarioActual() {
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
        UserDetails detallesUsuario = (UserDetails) autenticacion.getPrincipal();
        String correo = detallesUsuario.getUsername();

        Usuario usuario = repositorioUsuario
                .findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException(USUARIO_NO_ENCONTRADO));

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Long contarUsuarios() {
        return repositorioUsuario.count();
    }
}
