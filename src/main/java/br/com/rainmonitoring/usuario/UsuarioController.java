package br.com.rainmonitoring.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bcryptPassword;

    public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bcryptPassword) {
        this.usuarioRepository = usuarioRepository;
        this.bcryptPassword = bcryptPassword;
    }

    @PostMapping("user")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioForm usuarioForm){
        Usuario usuario = usuarioForm.converte(bcryptPassword);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("user/{usuarioEmail}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable("usuarioEmail") String usuarioEmail){
        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("user/{usuarioId}")
    public ResponseEntity<Usuario> editarUsuario(@RequestBody @Valid UsuarioEditForm usuarioEditForm,
                                                @PathVariable("usuarioId") Long usuarioId){
        Usuario usuario = usuarioEditForm.update(usuarioId, usuarioRepository, bcryptPassword);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("user/{usuarioId}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("usuarioId") Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado id: " + usuarioId));
        usuarioRepository.deleteById(usuarioId);
        return ResponseEntity.ok("Deletado com sucesso");
    }
}
