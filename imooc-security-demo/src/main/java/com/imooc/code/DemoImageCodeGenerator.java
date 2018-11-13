package com.imooc.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode create(HttpServletRequest request) {
		System.out.println("这里是以后实现的更高级的验证码生成");
		return null;
	}

}
