package br.com.rainmonitoring.usuario;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    @Email(message = "Por favor digite um email valido")
    private String email;

    @NotBlank
    @Length(min = 6,message = "A senha deve ter no minimo 6 caracteres")
    private String senha;

    @ElementCollection(fetch=FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Perfil> perfis = new HashSet<>();

    @Deprecated
    public Usuario() {
    }

    public Usuario(String nome,String email, String senha, Perfil perfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        perfis.add(perfil);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", perfis=" + perfis +
                '}';
    }
}
