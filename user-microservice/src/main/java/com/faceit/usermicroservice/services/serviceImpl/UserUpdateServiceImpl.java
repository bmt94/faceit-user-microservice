package com.faceit.usermicroservice.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.UserRepository;
import com.faceit.usermicroservice.services.UserUpdateService;


@Service
public class UserUpdateServiceImpl implements UserUpdateService {
	
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void updateUser(User user) {
		if(userRepo.existsById(user.getId())) userRepo.save(user);
	}

}
