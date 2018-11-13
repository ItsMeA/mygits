package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeFilter;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeController;
import com.imooc.security.core.validate.code.ValidateFilter;


@Configuration
public class BrowerSecurityConfiguer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	//配置passWordEncoder
	@Bean
	public PasswordEncoder passwordEncoder(){
		
		return new BCryptPasswordEncoder();
	
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		//jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//ImageCodeController imageCodeController=new ImageCodeController();
		ValidateFilter validateFilter=new ValidateFilter();
		validateFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		validateFilter.setSecurityProperties(securityProperties);
		validateFilter.afterPropertiesSet();
		
		SmsCodeFilter smsCodeFilter=new SmsCodeFilter();
		smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.afterPropertiesSet();
		
		//imageCodeController.set
		// TODO Auto-generated method stub
		
		http.addFilterBefore(validateFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
			.successHandler(imoocAuthenticationSuccessHandler)
			.failureHandler(imoocAuthenticationFailureHandler).
		and().
			rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(securityProperties.getBrower().getRememberMeSeconds())
			.userDetailsService(userDetailsService)
		.and()
			.authorizeRequests()
			.antMatchers("/authentication/require",
					securityProperties.getBrower().getLoginPage()
					,"/image/*").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable()
			.apply(smsCodeAuthenticationSecurityConfig);
		
		/*http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
	}
	

}
