package br.com.rainmonitoring.notificacaorisco;

import br.com.rainmonitoring.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class NotificacaoRiscoController {

    private NotificacaoRiscoRepository notificacaoRiscoRepository;

    private UsuarioRepository usuarioRepository;

    @Autowired
    SimpMessagingTemplate template;

    public NotificacaoRiscoController(NotificacaoRiscoRepository notificacaoRiscoRepository, UsuarioRepository usuarioRepository) {
        this.notificacaoRiscoRepository = notificacaoRiscoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/notificacao")
    public ResponseEntity<NotificacaoRisco> criarNotificacao(@RequestBody @Valid NotificacaoRiscoForm notificacaoRiscoForm){
        System.out.println(notificacaoRiscoForm);
        template.convertAndSend("/topic/message", notificacaoRiscoForm);
        //NotificacaoRisco notificacaoRisco = notificacaoRiscoForm.converte(usuarioRepository);
        return ResponseEntity.ok(null);
    }

    @MessageMapping("/message")
    public String receiveMessage(String message) throws Exception {
        System.out.println(message);
        return message;
    }
    @SendTo("/topic/message")
    public NotificacaoRiscoForm send(@Payload NotificacaoRiscoForm notificacaoRiscoForm) throws Exception {
        return notificacaoRiscoForm;
    }
}
