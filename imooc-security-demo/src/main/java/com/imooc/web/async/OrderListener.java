package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.imooc.web.dto.DefferedResultHolder;
import com.imooc.web.dto.MockQueue;
			
@Component//监听spring容器初始化完成,即spring管理的类已经加载完成
public class OrderListener implements ApplicationListener<ContextRefreshedEvent>{
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DefferedResultHolder defferedResultHolder;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		Runnable r=	new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					if(StringUtils.isNotBlank(mockQueue.getCompleteorder())){
						String orderNum=mockQueue.getCompleteorder();
						logger.info("返回订单处理结果:"+orderNum);
						 defferedResultHolder.getMap().get(orderNum).setResult("place order success");
						 mockQueue.setCompleteorder(null);
					}else{
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
		};
		Thread t=new Thread(r);
		t.start();
		
		
	}

}
