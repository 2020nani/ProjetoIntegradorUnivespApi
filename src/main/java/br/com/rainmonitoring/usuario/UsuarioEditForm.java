package br.com.rainmonitoring.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioEditForm {

    private String nome;

    private String email;

    private String senha;

    private Perfil perfil;

    public Usuario update(Long usuarioId, UsuarioRepository usuarioRepository, BCryptPasswordEncoder bcryptPassword) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado id: " + usuarioId));
        Usuario usuarioUpdate = new Usuario(
                nome!= null ? nome: usuario.getNome(),
                email!=null ? email : usuario.getEmail(),
                senha!=null ? bcryptPassword.encode(senha) : usuario.getSenha(),
                perfil!=null ? perfil : (Perfil) usuario.getPerfis().toArray()[0]);
        return usuarioUpdate;
    }
}
