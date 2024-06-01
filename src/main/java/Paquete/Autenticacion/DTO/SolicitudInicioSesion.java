package Paquete.Autenticacion.DTO;

import lombok.Data;

@Data
public class SolicitudInicioSesion {
    public String correo;
    public String contrasena;
}
