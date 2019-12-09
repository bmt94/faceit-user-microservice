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
import com.faceit.usermicroservice.repositories.UserRepository;
import com.faceit.usermicroservice.user.ExpectedTestUsers;

@SpringBootTest
public class GetUserByEmailTests 
{
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	@Test
	public void testGetByEmail(){				
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser1());		
		List<User> actualUsers = userRepo.findByEmail("ross@live.com");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByPasswordNegative(){
		List<User> actualUsers = userRepo.findByEmail("notexistingemail@mail.com");
		assertEquals(0, actualUsers.size());
	}
	

	
	
	
}