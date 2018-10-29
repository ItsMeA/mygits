package com.imooc.web.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handle, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
			System.out.println("afterCompletion");
			long start=(long)request.getAttribute("start");
			System.out.println("耗时 "+new Date().getTime());
			System.out.println("ex is "+ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handle, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");
		long start=(long)request.getAttribute("start");
		System.out.println("耗时 "+new Date().getTime());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handle) throws Exception {
		System.out.println("preHandle");
		System.out.println(((HandlerMethod)handle).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handle).getMethod().getName());
		request.setAttribute("start", new Date().getTime());
		// TODO Auto-generated method stub
		return true;
	}

}
