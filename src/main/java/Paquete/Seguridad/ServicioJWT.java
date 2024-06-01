package Paquete.Seguridad;

import java.util.Date;

import Paquete.Usuario.Dominio.ServicioUsuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServicioJWT {

    @Value("${jwt.clave-firma}")
    private String claveFirma;

    private final ServicioUsuario servicioUsuario;

    public String obtenerCorreo(String token) {
        return JWT.decode(token).getSubject();
    }

    public String obtenerReclamo(String token, String reclamo) {
        return JWT.decode(token).getClaim(reclamo).asString();
    }

    public String generarToken(UserDetails detallesUsuario){
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + 1000 * 60 * 60 * 10);

        Algorithm algoritmo = Algorithm.HMAC256(claveFirma);

        return JWT.create()
                .withSubject(detallesUsuario.getUsername())
                .withClaim("rol", detallesUsuario.getAuthorities().toArray()[0].toString())
                .withIssuedAt(ahora)
                .withExpiresAt(expiracion)
                .sign(algoritmo);
    }

    public void validarToken(String token, String correo) throws AuthenticationException {

        JWT.require(Algorithm.HMAC256(claveFirma)).build().verify(token);

        UserDetails detallesUsuario = servicioUsuario.servicioDetallesUsuario().loadUserByUsername(correo);

        SecurityContext contexto = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken tokenAutenticacion = new UsernamePasswordAuthenticationToken(
                detallesUsuario,
                token,
                detallesUsuario.getAuthorities()
        );

        contexto.setAuthentication(tokenAutenticacion);
        SecurityContextHolder.setContext(contexto);
    }
}