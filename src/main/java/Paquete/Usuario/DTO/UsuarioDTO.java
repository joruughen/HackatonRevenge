package Paquete.Usuario.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private Integer edad;
    private String correo;
}
