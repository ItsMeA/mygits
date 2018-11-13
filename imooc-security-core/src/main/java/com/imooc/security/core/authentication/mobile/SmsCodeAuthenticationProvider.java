package com.imooc.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider{

	
	private UserDetailsService userDetailsService ;
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		SmscodeAuthenticationToken	authenticationToken=(SmscodeAuthenticationToken)authentication;
		UserDetails  user=userDetailsService.loadUserByUsername((String)authenticationToken.getPrincipal());
		if(user==null){
			throw new InternalAuthenticationServiceException("找不到用户信息");
		}
		SmscodeAuthenticationToken authenticationResult=new SmscodeAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return SmscodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	

}
