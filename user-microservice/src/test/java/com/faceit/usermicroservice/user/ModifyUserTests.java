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
public class ModifyUserTests 
{
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional
	@Test
	public void testModifyUser(){				
		User userModified = ExpectedTestUsers.getUser2();
		userModified.setCountry("United Kingdom");
		userModified.setLastName("Karen");
		userRepo.save(userModified);
		Optional<User> actualUser = userRepo.findById(userModified.getId());
		assertEquals(userModified, actualUser.get());
	}
	

	
}