package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ValidateCode {
	
	
	
	private String code;
	
	private Date createdDate;
	
	private Date endTime;
	
	

	/*public ImageCode(BufferedImage image, String code, int expireIn) {
		this.image = image;
		this.code = code;
		//this.expireTime = new Date().getSeconds()+expireIn;
	}*/
	
	
	
	public ValidateCode( String code, Date expireTime) {
		
		this.code = code;
		this.createdDate = expireTime;
	}

	public ValidateCode() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	
	

}
