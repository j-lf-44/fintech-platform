package com.jlf.pmt.user.mapper;

import org.springframework.stereotype.Component;

import com.jlf.pmt.user.User;
import com.jlf.pmt.user.dto.CreateUserRequest;
import com.jlf.pmt.user.dto.UserResponse;

@Component
public class UserMapper {
	
	public User toEntity(CreateUserRequest request) {
		
		User user = new User();
		
		
		return user;
	}
	
	public UserResponse toResponse(User user) {
		UserResponse response = new UserResponse();
		
		return response;
	}

}
