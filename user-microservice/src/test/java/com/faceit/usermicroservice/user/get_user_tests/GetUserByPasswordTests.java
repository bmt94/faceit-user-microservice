package com.faceit.usermicroservice.user.get_user_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.entities.User;
import  com.faceit.usermicroservice.repositories.user_repository.UserRepository;
import com.faceit.usermicroservice.user.ExpectedTestUsers;

@SpringBootTest
public class GetUserByPasswordTests 
{
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	@Test
	public void testGetByPasswordUnique(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser3());		
		List<User> actualUsers = userRepo.findByPassword("$2y$10$c8ETCvUvqP1ELKM43rQ.guAK/bn7obUS5M6G7qQgOIOJxjwgnIkf2");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByPasswordMultiple(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser2());	
		expectedUsers.add(ExpectedTestUsers.getUser1());	
		List<User> actualUsers = userRepo.findByPassword("$2y$10$maEUORd2DLNYcbrmN9IlDu.ZI5itTB7AkXrw1wcXJkEkKopI7tXhS");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByPasswordNegative(){
		List<User> actualUsers = userRepo.findByPassword("wrongpassword");
		assertEquals(0, actualUsers.size());
	}
	

	
	
	
}





