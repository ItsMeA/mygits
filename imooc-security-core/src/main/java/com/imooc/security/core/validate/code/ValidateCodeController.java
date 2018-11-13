package com.imooc.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;


@RestController
public class ValidateCodeController {
	
	public final static String SESSION_KEY="SESSION_KEY_IMAGE_CODE_";
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
	
	@Autowired
	private ValidateCodeGenerator imageCodeGenerator;
	@Autowired
	private ValidateCodeGenerator smsCodeGenerator;
	@Autowired
	private SmsCodeSender smsCodeSender;
	@GetMapping("/image/code")
	public void createImageCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//1生成一个图片验证码;
		ImageCode imageCode=(ImageCode)imageCodeGenerator.create(request);
		
		//2将图片验证码放入session中
		sessionStrategy.setAttribute(new ServletWebRequest(request) , SESSION_KEY, imageCode);
		//3将图片验证码写入输出流中
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
		
	}

	@GetMapping("/image/smsCode")
	public void createsmsCode(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletRequestBindingException{
		
		//1生成一个图片验证码;
		ValidateCode validateCode=smsCodeGenerator.create(request);
		
		//2将图片验证码放入session中
		sessionStrategy.setAttribute(new ServletWebRequest(request) , SESSION_KEY+"SMS", validateCode);
		//3发短信
		String mobile=ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile, validateCode.getCode());
		
		
	}
	

}
