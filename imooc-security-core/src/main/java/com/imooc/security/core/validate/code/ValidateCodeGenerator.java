package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

	
	public ValidateCode create(HttpServletRequest request);

	
}
