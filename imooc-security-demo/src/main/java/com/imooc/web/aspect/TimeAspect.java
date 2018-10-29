package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
	
	@Around("execution(* com.imooc.web.controller.Usercontroller.*(..))")
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("aspect start");
		Object[] args = pjp.getArgs();
		for (Object object : args) {
			System.out.println("arg is "+object);
		}
		
		long start=new Date().getTime();
		Object object = pjp.proceed();
		System.out.println("aspect 耗时:"+(new Date().getTime()-start));
		System.out.println("aspect finish");
		
		
		return object;

	}
	
	
	

}
