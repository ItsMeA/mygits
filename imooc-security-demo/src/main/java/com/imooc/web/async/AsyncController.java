package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.imooc.web.dto.DefferedResultHolder;
import com.imooc.web.dto.MockQueue;




@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DefferedResultHolder defferedResultHolder;
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	@RequestMapping("/order")
	public DeferredResult<String>  order() throws Exception{
		/*logger.info("主线程开启"); 
		Callable<String> result=new Callable<String>(){

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				logger.info("fu线程开启"); 
				Thread.sleep(1000);
				logger.info("fu线程返回");
				return "success";
			}
			
		};
		
		logger.info("主线程返回");*/
		logger.info("主线程开启");
		String ordernub=RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceorder(ordernub);
		DeferredResult<String> result=new DeferredResult<>();
		defferedResultHolder.getMap().put(ordernub, result);
		result.setResult("这是不是一次性就返回浏览器展示了");
		
		return result;
		
	}

}
