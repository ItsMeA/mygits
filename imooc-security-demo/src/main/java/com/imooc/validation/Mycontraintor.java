package com.imooc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Mycontraintor implements ConstraintValidator<Mycontraint, Object> {

	@Override
	public void initialize(Mycontraint constraintAnnotation) {
		// TODO Auto-generated method stub
		System.out.println("Mycontraintor"+" init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		System.out.println("value:"+value);
		return false;
	}

}
