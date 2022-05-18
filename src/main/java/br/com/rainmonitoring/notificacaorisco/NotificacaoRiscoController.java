package br.com.rainmonitoring.notificacaorisco;

import br.com.rainmonitoring.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class NotificacaoRiscoController {

    private NotificacaoRiscoRepository notificacaoRiscoRepository;

    private UsuarioRepository usuarioRepository;

    public NotificacaoRiscoController(NotificacaoRiscoRepository notificacaoRiscoRepository, UsuarioRepository usuarioRepository) {
        this.notificacaoRiscoRepository = notificacaoRiscoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("notificacao")
    public ResponseEntity<NotificacaoRisco> criarNotificacao(@RequestBody @Valid NotificacaoRiscoForm notificacaoRiscoForm){
        NotificacaoRisco notificacaoRisco = notificacaoRiscoForm.converte(usuarioRepository);
        return ResponseEntity.ok(notificacaoRiscoRepository.save(notificacaoRisco));
    }

    @GetMapping("notificacao")
    public ResponseEntity<List<NotificacaoRisco>> buscarNotificacao(){
        List<NotificacaoRisco> notificacaoRiscoList = notificacaoRiscoRepository.findAll();
        return ResponseEntity.ok(notificacaoRiscoList);
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
        System.out.println(message);
        return message;
    }
}
