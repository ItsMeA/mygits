package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ImageCode extends ValidateCode {
	
	private BufferedImage image;
	
	private Date endTime;
	
	

	/*public ImageCode(BufferedImage image, String code, int expireIn) {
		this.image = image;
		this.code = code;
		//this.expireTime = new Date().getSeconds()+expireIn;
	}*/
	
	
	
	public ImageCode(BufferedImage image, String code, Date expireTime) {
		super(code,expireTime);
		this.image = image;
		
	}

	public ImageCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	
	

}
