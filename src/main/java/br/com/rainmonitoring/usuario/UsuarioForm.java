package br.com.rainmonitoring.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UsuarioForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Usuario converte() {
        return new Usuario(name, email, password);
    }
}
