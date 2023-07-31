package com.innoventes.test.app.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Task 6
public class EvenNumberOrZeroValidator implements ConstraintValidator<EvenNumberOrZero,Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context){
        if(value==null){
            return true;
        }
        long number=value.longValue();
        return number==0||number%2==0;
    }
}
