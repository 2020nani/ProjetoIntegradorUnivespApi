package br.com.rainmonitoring.notificacaorisco;

import br.com.rainmonitoring.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class NotificacaoRisco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private Regiao regiao;

    @NotNull
    private String cidade;

    @NotNull
    private String descricao;

    @NotNull
    private LocalDate criacao = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getCriacao() {
        return criacao;
    }

    public NotificacaoRisco(Regiao regiao, String cidade, String descricao, Usuario usuario) {
        this.regiao = regiao;
        this.cidade = cidade;
        this.descricao = descricao;
        this.usuario = usuario;
    }
}
