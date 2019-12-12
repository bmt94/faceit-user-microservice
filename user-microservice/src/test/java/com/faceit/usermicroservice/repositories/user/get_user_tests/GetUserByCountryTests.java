package com.faceit.usermicroservice.repositories.user.get_user_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.ExpectedTestUsers;
import com.faceit.usermicroservice.entities.User;
import  com.faceit.usermicroservice.repositories.user_repository.UserRepository;

@SpringBootTest
public class GetUserByCountryTests 
{
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional
	@Test
	public void testGetByCountryUnique(){				
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser3());		
		List<User> actualUsers = userRepo.findByCountry("Italy");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByCountryMultiple(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser4());		
		expectedUsers.add(ExpectedTestUsers.getUser1());		
		List<User> actualUsers = userRepo.findByCountry("USA");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByCountryNegative(){
		List<User> actualUsers = userRepo.findByCountry("Poland");
		assertEquals(0, actualUsers.size());
	}

	
}