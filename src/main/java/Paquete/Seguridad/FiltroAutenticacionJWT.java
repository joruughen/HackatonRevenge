package Paquete.Seguridad;

import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FiltroAutenticacionJWT extends OncePerRequestFilter {

    private final ServicioJWT servicioJWT;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest solicitud, @NonNull HttpServletResponse respuesta, @NonNull FilterChain cadenaFiltros) throws IOException {
        String encabezado = solicitud.getHeader("Authorization");
        String token;
        String correo;

        try {
            // Si no hay encabezado o no empieza con "Bearer"
            if (!StringUtils.hasText(encabezado) || !StringUtils.startsWithIgnoreCase(encabezado, "Bearer")) {
                cadenaFiltros.doFilter(solicitud, respuesta); // Continúa con el siguiente filtro en la cadena
                return; // Retorna
            }

            token = encabezado.substring(7);
            correo = servicioJWT.obtenerCorreo(token);

            // Si hay correo y no hay una autenticación en el contexto de seguridad
            if (StringUtils.hasText(correo) && SecurityContextHolder.getContext().getAuthentication() == null) {
                servicioJWT.validarToken(token, correo);
            }

            // Continúa con el siguiente filtro en la cadena
            cadenaFiltros.doFilter(solicitud, respuesta);

        } catch (JWTDecodeException ex) {
            respuesta.setStatus(HttpStatus.BAD_REQUEST.value());
            System.out.println(ex.getMessage());
            respuesta.getWriter().write("Token inválido.");
        } catch (ServletException ex) {
            respuesta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            System.out.println(ex.getMessage());
            respuesta.getWriter().write("Perdón, error del filtro.");
        }
    }
}
