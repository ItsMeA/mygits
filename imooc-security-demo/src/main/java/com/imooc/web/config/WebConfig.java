package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptors.TimeInterceptor;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		//configurer.registerCallableInterceptors(interceptors);
		//configurer.registerDeferredResultInterceptors(interceptors);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//registry.addInterceptor(timeInterceptor);
	}
	
	//@Bean
	public FilterRegistrationBean timeFilter(){
		FilterRegistrationBean filBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filBean.setFilter(timeFilter);
		//添加过滤器拦截路径
		List<String> urls=new ArrayList();
		urls.add("/*");
		filBean.setUrlPatterns(urls);
		
		return filBean;
	}
	

}
