package com.faceit.usermicroservice.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.services.UserResponseService;
import com.faceit.usermicroservice.web_entities.UserResponse;

@Service
public class UserResponseServiceImpl implements UserResponseService {

	@Override
	public UserResponse UserToResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setCountry(user.getCountry());
		userResponse.setEmail(user.getEmail());
		userResponse.setNickname(user.getNickname());
		return userResponse;
	}

	@Override
	public List<UserResponse> UserToResponse(List<User> users) {
		List<UserResponse> userResponses = new ArrayList<>();
		for(User user: users) {
			userResponses.add(UserToResponse(user));
		}
		return userResponses;
	}

	@Override
	public List<UserResponse> UserToResponse(Iterable<User> users) {
		List<UserResponse> userResponses = new ArrayList<>();
		for(User user: users) {
			userResponses.add(UserToResponse(user));
		}
		return userResponses;
	}

}
