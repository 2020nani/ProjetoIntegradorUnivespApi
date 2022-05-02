package br.com.rainmonitoring.usuario;

import br.com.rainmonitoring.validcustom.SenhaPadrao;
import br.com.rainmonitoring.validcustom.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioForm {

    @NotBlank
    private String nome;

    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    @Email(message = "Por favor digite um email valido")
    private String email;

    @NotBlank
    @Length(min = 6,message = "A senha deve ter no minimo 6 caracteres")
    @SenhaPadrao
    private String senha;

    @NotNull
    private Perfil perfil;

    public UsuarioForm(String nome, String email, String senha, Perfil perfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Usuario converte(BCryptPasswordEncoder bcryptPassword) {
        return new Usuario(nome, email, bcryptPassword.encode(senha),perfil);
    }

}
