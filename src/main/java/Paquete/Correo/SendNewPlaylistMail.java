package Paquete.Correo;

import Paquete.Usuario.Dominio.Usuario;
import Paquete.Usuario.Infraestructura.RepositorioUsuario;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SendNewPlaylistMail extends ApplicationEvent {
    RepositorioUsuario UserRepository;

    private final Mail mail;

    public SendNewPlaylistMail(Usuario usuario, String email) {
        super(usuario.getCorreo());
        Map<String, Object> properties = new HashMap<>();
        properties.put("userName", usuario.getNombre());
        properties.put("playlistName", "Hola"); //todo: fix this

        this.mail = Mail.builder()
                .from(email)
                .to(usuario.getCorreo())
                .htmlTemplate(new Mail.HtmlTemplate("NewPlaylistTemplate", properties))
                .subject("Has generado una nueva playlist")
                .build();
    }

}
