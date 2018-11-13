package com.imooc.security.core.authentication.mobile;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeController;
import com.imooc.security.core.validate.code.ValidateException;

public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean{
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	
	private SecurityProperties securityProperties;
	private Set<String> urls=new HashSet<>();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configurls=StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(), ",");
	if(configurls!=null){
		for (String url : configurls) {
			urls.add(url);
		}
	}
		urls.add("/authentication/mobile");
	}
	

	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
	
	private AntPathMatcher pathMatcher=new AntPathMatcher();
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info(request.getRequestURI()+":"+request.getMethod());
		
		boolean action=false;
		for (String url : urls) {
			if(pathMatcher.match(url, request.getRequestURI())){
				action=true;
			}
		}
		if(action){
			
			try {
				validate(new ServletWebRequest(request));
				
			} catch (ValidateException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
			filterChain.doFilter(request, response);
	
		
		
	}

	private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
		// TODO Auto-generated method stub
		ValidateCode codeInSession=(ValidateCode) sessionStrategy.getAttribute(servletWebRequest, 
				ValidateCodeController.SESSION_KEY+"SMS");
		String codeInRequest=ServletRequestUtils.getStringParameter( servletWebRequest.getRequest(), "smsCode");
		
		if(StringUtils.isBlank(codeInRequest)){
			throw new	ValidateException("验证码不能为空");
		}
		
		if(codeInSession==null){
			throw new	ValidateException("验证码不存在");
		}
		
		if((new Date().getTime()-codeInSession.getCreatedDate().getTime())>(1000*120)){
			sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY+"SMS");
			throw new	ValidateException("验证码已经过期");
		}
		
		if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
			throw new	ValidateException("验证码不匹配");
		}
		
		sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY+"SMS");
	}
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(
			AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	

}
