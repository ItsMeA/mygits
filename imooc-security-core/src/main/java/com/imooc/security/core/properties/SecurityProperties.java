package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@ConfigurationProperties(prefix="imooc.security")
//@PropertySource("classpath:application.properties")
public class SecurityProperties {
	
private	BrowerProperties brower=new BrowerProperties();
private ValidateCodeProperties code=new ValidateCodeProperties();

public BrowerProperties getBrower() {
	return brower;
}

public void setBrower(BrowerProperties brower) {
	this.brower = brower;
}

public ValidateCodeProperties getCode() {
	return code;
}

public void setCode(ValidateCodeProperties code) {
	this.code = code;
}



}
