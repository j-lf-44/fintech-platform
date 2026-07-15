package com.jlf.pmt.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jlf.pmt.user.dto.CreateUserRequest;
import com.jlf.pmt.user.dto.UserResponse;
import com.jlf.pmt.user.exceptions.UserNotFoundException;
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
	
	public UserResponse getUser(UUID id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found: " + id));
		
		return mapper.toResponse(user);
	}
	
	public void deleteUser(UUID id) {
		repository.deleteById(id);
	}

}
