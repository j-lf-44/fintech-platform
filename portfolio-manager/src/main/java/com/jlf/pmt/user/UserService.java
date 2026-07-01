package com.jlf.pmt.user;

import org.springframework.stereotype.Service;

import com.jlf.pmt.user.dto.CreateUserRequest;
import com.jlf.pmt.user.dto.UserResponse;
import com.jlf.pmt.user.mapper.UserMapper;

@Service
public class UserService {
	
	private final UserMapper mapper;
	private final UserRepository repository;
	
	public UserService(UserMapper mapper, UserRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}
	
	public UserResponse createUser(CreateUserRequest request) {
		
		User user = mapper.toEntity(request);
		
		User savedUser = repository.save(user);
		
		return mapper.toResponse(savedUser);
	}

}
