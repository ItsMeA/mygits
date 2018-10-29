package com.imooc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.METHOD,ElementType.FIELD})//target注解,指定可以标注在什么上,这里指定可以标注在方法上和字段上
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=Mycontraintor.class)
public @interface Mycontraint {
	
	String message();
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
