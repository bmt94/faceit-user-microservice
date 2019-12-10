package com.faceit.usermicroservice.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.UserRepository;
import com.faceit.usermicroservice.services.UserUpdateService;

@SpringBootTest
public class ModifyUserTests 
{
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserUpdateService userUpdateter;
	
	
	@Transactional
	@Test
	public void testModifyUser(){				
		User userModified = ExpectedTestUsers.getUser2();
		userModified.setCountry("United Kingdom");
		userModified.setLastName("Karen");
		
		userUpdateter.updateUser(userModified);	
		Optional<User> actualUser = userRepo.findById(userModified.getId());
		assertEquals(userModified, actualUser.get());
	}
	
	
	@Transactional
	@Test
	public void testModifyNonExistantUser(){				
		User userModified = new User();
		userModified.setId(100);
		userModified.setCountry("Germany");
		
		userUpdateter.updateUser(userModified);	
		Optional<User> actualUser = userRepo.findById(userModified.getId()); 
		assertEquals(false, actualUser.isPresent());
	}
	

	
}