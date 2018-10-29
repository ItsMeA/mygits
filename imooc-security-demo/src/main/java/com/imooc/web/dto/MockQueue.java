package com.imooc.web.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	private String placeorder;
	private String completeorder;
	public String getPlaceorder() {
		return placeorder;
	}
	public void setPlaceorder(final String placeorder) throws Exception {
		
		logger.info("placeorder:开始下单");
	new Thread(new Runnable() {
		
	

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
				logger.info("下单处理完毕");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}).start();
	Thread.sleep(1000);
	this.completeorder = placeorder;
		
	
			
		

		
	}
	public String getCompleteorder() {
		return completeorder;
	}
	public void setCompleteorder(String completeorder) {
		this.completeorder = completeorder;
	}
	
	
	

}
