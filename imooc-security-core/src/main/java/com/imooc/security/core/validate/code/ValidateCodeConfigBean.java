package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;


@Configuration
public class ValidateCodeConfigBean {
	@Autowired
	private SecurityProperties securityProperties;
	@Bean
	@ConditionalOnMissingBean(name="imageCodeGenerator")
	ValidateCodeGenerator imageCodeGenerator(){
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	SmsCodeSender smsCodeSender(){
		
		return new DefaultSmsCodeSender();
	}
	
	
}
