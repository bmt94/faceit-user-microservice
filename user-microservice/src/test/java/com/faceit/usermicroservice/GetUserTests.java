package com.faceit.usermicroservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.UserRepository;

@SpringBootTest
class GetUserTests 
{
	@Autowired
	UserRepository userRepo;

	
	
	@Transactional
	@Test
	public void testGetUserByName(){
		User userExpected = new User();
		userExpected.setId(1);
		userExpected.setCountry("USA");
		userExpected.setEmail("ross@live.com");
		userExpected.setFirstName("Ross");
		userExpected.setLastName("Geller");
		userExpected.setNickname("rossg");
		userExpected.setPassword("$2y$10$7W.W0LlZyAUGkXc84skK8uiJdfLseIJ2GtrAm9ewSfAMxCkMhyzxm");
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(userExpected);
		
		List<User> actualUsers = userRepo.findByName("ross", "geller");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetUserByIncorrectName(){
		List<User> actualUsers = userRepo.findByName("noname", "nolastname");
		assertEquals(0, actualUsers.size());
	}
	
	
	
}
