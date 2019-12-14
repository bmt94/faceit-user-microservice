package com.faceit.usermicroservice.services;

import java.util.List;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.web_entities.UserResponse;

public interface UserResponseService {
	
	UserResponse UserToResponse(User user);
	List<UserResponse> UserToResponse(Iterable<User> users);
	
}
