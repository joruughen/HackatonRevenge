package Paquete.Autenticacion.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitudRegistro {

    @NotNull
    private String nombre;

    @NotNull
    private String correo;

    @NotNull
    private String contrasena;

}
