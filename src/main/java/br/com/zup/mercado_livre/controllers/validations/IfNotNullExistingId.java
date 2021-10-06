package br.com.zup.mercado_livre.controllers.validations;

import javax.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {IfNotNullExistingIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IfNotNullExistingId {

    String message() default "O id do atributo informado não existe!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
