package com.marcosmartinezdeveloper.practiceProject.controllers.validators.projectAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.marcosmartinezdeveloper.practiceProject.controllers.validators.SignUpValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SignUpValidator.class)
public @interface SignUpValid {

    String message() default "Empty form";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
