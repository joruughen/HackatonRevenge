package Paquete.Autenticacion;

import Paquete.Autenticacion.DTO.RespuestaJWT;
import Paquete.Autenticacion.DTO.SolicitudInicioSesion;
import Paquete.Autenticacion.DTO.SolicitudRegistro;
import Paquete.Excepciones.ExcepcionUsuarioYaExiste;
import Paquete.Seguridad.ServicioJWT;
import Paquete.Usuario.Dominio.Rol;
import Paquete.Usuario.Dominio.Usuario;
import Paquete.Usuario.Infraestructura.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioAutenticacion {

    private final RepositorioUsuario repositorioUsuario;
    private final ServicioJWT servicioJWT;
    private final PasswordEncoder encriptadorContrasenas;
    private final ModelMapper mapeadorModelos;

    public RespuestaJWT iniciarSesion(SolicitudInicioSesion solicitud) {
        Optional<Usuario> usuario = repositorioUsuario.findByCorreo(solicitud.getCorreo());

        if (usuario.isEmpty()) throw new UsernameNotFoundException("El correo no está registrado.");

        if (!encriptadorContrasenas.matches(solicitud.getContrasena(), usuario.get().getContrasena()))
            throw new IllegalArgumentException("Credenciales inconrrectas.");

        RespuestaJWT respuesta = new RespuestaJWT();
        respuesta.setToken(servicioJWT.generarToken(usuario.get()));
        return respuesta;
    }

    public RespuestaJWT registrar(SolicitudRegistro solicitud) {
        Optional<Usuario> usuario = repositorioUsuario.findByCorreo(solicitud.getCorreo());
        if (usuario.isPresent()) throw new ExcepcionUsuarioYaExiste("El correo ya está registrado.");

        Usuario nuevoUsuario = mapeadorModelos.map(solicitud, Usuario.class);
        nuevoUsuario.setContrasena(encriptadorContrasenas.encode(solicitud.getContrasena()));
        nuevoUsuario.setCreadoAlas(LocalDateTime.now());

        nuevoUsuario.setRol(Rol.USUARIO);

        repositorioUsuario.save(nuevoUsuario);

        RespuestaJWT respuesta = new RespuestaJWT();
        respuesta.setToken(servicioJWT.generarToken(nuevoUsuario));
        return respuesta;
    }
}
