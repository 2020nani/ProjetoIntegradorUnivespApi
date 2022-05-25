package br.com.rainmonitoring.notificacaorisco;

import br.com.rainmonitoring.usuario.Usuario;
import br.com.rainmonitoring.usuario.UsuarioRepository;
import br.com.rainmonitoring.validcustom.ExisteObjeto;

import javax.validation.constraints.NotNull;


public class NotificacaoRiscoForm {

    @NotNull
    private Regiao regiao;

    @NotNull
    private String cidade;

    @NotNull
    private String descricao;

    @ExisteObjeto(domainClass = Usuario.class, fieldName = "id")
    private Long usuarioId;

    @NotNull
    private Boolean isRead;

    public Regiao getRegiao() {
        return regiao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Boolean getRead() {
        return isRead;
    }

    public NotificacaoRiscoForm(Regiao regiao, String cidade, String descricao, Long usuarioId, Boolean isRead) {
        this.regiao = regiao;
        this.cidade = cidade;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.isRead = isRead;
    }

    public NotificacaoRisco converte(UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        return new NotificacaoRisco(regiao, cidade, descricao, usuario);
    }

    @Override
    public String toString() {
        return "NotificacaoRiscoForm{" +
                "regiao=" + regiao +
                ", cidade='" + cidade + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuarioId=" + usuarioId +
                ", isRead=" + isRead +
                '}';
    }
}
