package com.faceit.usermicroservice.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.UserRepository;

@SpringBootTest
public class AddUserTests 
{
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional
	@Test
	public void tesAddUser(){				
		User userNew = new User();
		userNew.setCountry("Scotland");
		userNew.setEmail("chandler@live.com");
		userNew.setFirstName("Chandler");
		userNew.setLastName("Bing");
		userNew.setNickname("chandlerb");
		userNew.setPassword("$2y$10$HvhIUiCXMJyITv1E5GtMNuUGeTE0xfcDTNGCXfqaJjo7kHBlhxZM2");
		
		userRepo.save(userNew);
		Optional<User> actualUsers = userRepo.findById(userNew.getId());
		assertEquals(userNew, actualUsers.get());
	}
	

	
}