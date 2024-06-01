package Paquete.Correo;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewPlaylistEmailListener {
    private final EmailService emailService;

    @Autowired
    public NewPlaylistEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    @Async
    public void handleEmailEvent(SendNewPlaylistMail event) throws MessagingException, IOException {
        emailService.sendEmail(event.getMail());
    }
}
