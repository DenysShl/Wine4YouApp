package com.sommelier.wine4you.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "The email must be Latin letters, " +
            "digits, and some special characters: " +
            "plus (+), hyphen (-), underline (_), dot (.)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
