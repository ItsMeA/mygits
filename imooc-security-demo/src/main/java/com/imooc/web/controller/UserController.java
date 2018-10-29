package com.imooc.web.controller;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.exception.UserNotExistException;
import com.imooc.web.dto.User;
import com.imooc.web.dto.UserCondition;


@RestController
@RequestMapping("/user")
public class Usercontroller {
	
	
	
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> queryuser(  UserCondition user){
		throw new UserNotExistException("1");
		//System.out.println("进入到访问程序");
		//return null;
	}
	
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	@ApiOperation(value="用户查询详细信息")
	public User queryUserInfo(@PathVariable String id){
		User user=new User();
		user.setName("tom");
		return user;
	}
	
	@PostMapping
	public User createUser(@Valid @RequestBody User user){
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User updateUser(@Valid @RequestBody User user,BindingResult errors){
		if(errors.hasErrors()){
			List<ObjectError> allErrors = errors.getAllErrors();
			for (ObjectError objectError : allErrors) {
				FieldError fieldError=(FieldError)objectError;
				String message=fieldError.getField()+"  "+fieldError.getDefaultMessage();
				System.out.println(message);
			}
		}
		
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void deleteUser(@PathVariable String id){
		User user=new User();
		System.out.println(id);
		
		
	}
	

}
