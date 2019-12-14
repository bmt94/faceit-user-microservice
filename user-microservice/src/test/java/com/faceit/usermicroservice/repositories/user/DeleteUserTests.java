package com.faceit.usermicroservice.repositories.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.ExpectedTestUsers;
import com.faceit.usermicroservice.entities.User;
import  com.faceit.usermicroservice.repositories.user_repository.UserRepository;

@SpringBootTest
public class DeleteUserTests 
{
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional
	@Test
	public void testDeleteUser(){				
		User userToDelete = ExpectedTestUsers.getUser3();
		userRepo.delete(userToDelete);
		Optional<User> actualUser = userRepo.findById(userToDelete.getId());
		assertEquals(false, actualUser.isPresent());
	}

	@Transactional
	@Test
	public void testDeleteUserByID(){				
		userRepo.deleteById(1);
		Optional<User> actualUser = userRepo.findById(1);
		assertEquals(false, actualUser.isPresent());
	}

	
}