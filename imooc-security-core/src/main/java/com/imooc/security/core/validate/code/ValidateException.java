package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateException extends AuthenticationException{

	public ValidateException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020333551866598473L;

}
