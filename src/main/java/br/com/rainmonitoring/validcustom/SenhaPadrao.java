package br.com.rainmonitoring.validcustom;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;


@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_-])[^\\s]{6,}$",
message = "A senha deve conter ao menos um numero, um dos caracteres especiais (@#$%^&+=_-),uma letra maiuscula e uma letra minuscula e ter no minimo 6 caracteres")
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = { })
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaPadrao {

    String message() default "A senha deve conter numeros, caracteres especiais, letras e ter no minimo 6 caracteres";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
