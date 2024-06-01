package Paquete.Configuracion;

import Paquete.Excepciones.ExcepcionUsuarioYaExiste;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    // Argumento ilegal o inapropiado.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    // Datos de entrada no cumplen las restricciones de validación.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String manejarMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getMessage();
    }

    // Solicitud con datos inválidos o incompletos.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String manejarMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ex.getMessage();
    }

    // Problema durante el proceso de autenticación.
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public String manejarAuthenticationException(AuthenticationException ex) {
        return ex.getMessage();
    }

    // Acceder sin los permisos necesarios.
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String manejarAccessDeniedException(AccessDeniedException ex) {
        return ex.getMessage();
    }

    // No se encuentra el correo.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public String manejarUsernameNotFoundException(UsernameNotFoundException ex) {
        return ex.getMessage();
    }

    // Recurso no encontrado.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public String manejarNoResourceFoundException(NoResourceFoundException ex) {
        return ex.getMessage();
    }

    // Correo ya regsitrado.
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExcepcionUsuarioYaExiste.class)
    public String manejarExcepcionUsuarioYaExiste(ExcepcionUsuarioYaExiste ex) {
        return ex.getMessage();
    }

    // Error inesperado en la aplicación.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        System.out.println(ex.getMessage());
        return "Perdón, error del backend.";
    }
}