package com.innoventes.test.app.dto;

//import jdk.jfr.Relational;

import javax.validation.*;
import java.lang.annotation.*;

//Task 6
@Documented
@Constraint(validatedBy=EvenNumberOrZeroValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

public @interface EvenNumberOrZero {
    String message() default "Only even numbers or zero are allowed.";
    Class<?> []groups() default {};
    Class<? extends Payload>[]payload()default {};
}
