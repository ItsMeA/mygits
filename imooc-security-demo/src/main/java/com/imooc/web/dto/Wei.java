package com.imooc.web.dto;

public class Wei {
	
	private static Wei Instance;
	private String name;
	private String password;
	
	
	private Wei() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	public static Wei getInstance(){
		if(Instance==null){
			return new  Wei();
		}
		
		return Instance;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}
	

}
