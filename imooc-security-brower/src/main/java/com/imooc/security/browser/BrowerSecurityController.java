package com.imooc.security.browser;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;


@RestController
public class BrowerSecurityController {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	private RequestCache requestCache=new HttpSessionRequestCache();
	
	private RedirectStrategy  redirectStrategy=new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties properties;
	
	@RequestMapping("/authentication/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException{
		SavedRequest request2 = requestCache.getRequest(request, response);
		logger.info("客户page:"+properties.getBrower().getLoginPage());
		if(request2!=null){
			String targetUrl = request2.getRedirectUrl();
			logger.info("目标url:"+targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
				redirectStrategy.sendRedirect(request, response, properties.getBrower().getLoginPage());
			}
			
			
		}
		
		return new SimpleResponse("访问服务需要认证,请引导用户到登录页");
	}

}
