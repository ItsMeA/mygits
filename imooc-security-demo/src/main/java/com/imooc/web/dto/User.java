package com.imooc.web.dto;

import java.util.Date;






import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validation.Mycontraint;

public class User {
	
	public interface UserSimpleView{}
	public interface UserDetailView extends UserSimpleView{}
	@JsonView(UserSimpleView.class)
	private String id;
	
	@Past(message="生日必须为过去日期")
	private Date birthday;
	
	
	@Mycontraint(message="这是一个测试")
	private String name;
	@NotBlank(message="密码不能为空")
	private String password;
	@JsonView(UserSimpleView.class)
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
