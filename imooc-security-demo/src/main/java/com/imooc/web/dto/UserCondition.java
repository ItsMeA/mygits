package com.imooc.web.dto;

import org.hibernate.validator.constraints.NotBlank;

public class UserCondition {
	
	private String username;
	private String age;
	private String ageto;
	@NotBlank
	private String xxx;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAgeto() {
		return ageto;
	}
	public void setAgeto(String ageto) {
		this.ageto = ageto;
	}
	public String getXxx() {
		return xxx;
	}
	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	@Override
	public String toString() {
		return "UserCondition [username=" + username + ", age=" + age
				+ ", ageto=" + ageto + ", xxx=" + xxx + "]";
	}
	

}
