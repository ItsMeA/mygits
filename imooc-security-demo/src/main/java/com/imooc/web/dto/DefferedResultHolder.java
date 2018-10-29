package com.imooc.web.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
@Component
public class DefferedResultHolder {
	
	private Map<String,DeferredResult> map=new HashMap<>();

	public Map<String, DeferredResult> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult> map) {
		this.map = map;
	}
	

}
