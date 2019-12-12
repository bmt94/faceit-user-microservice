package com.faceit.usermicroservice.repositories.user.get_user_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.entities.User;
import  com.faceit.usermicroservice.repositories.user_repository.UserRepository;
import com.faceit.usermicroservice.ExpectedTestUsers;

@SpringBootTest
public class GetUserByNameTests 
{
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional
	@Test
	public void testGetByFullName(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser1());		
		List<User> actualUsers = userRepo.findByName("ross", "geller");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByFullNameNegative(){
		List<User> actualUsers = userRepo.findByName("noname", "nolastname");
		assertEquals(0, actualUsers.size());
	}
	
	
	@Transactional
	@Test
	public void testGetByFirstName(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser1());		
		List<User> actualUsers = userRepo.findByFirstName("ross");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByFirstNameNegative(){
		List<User> actualUsers = userRepo.findByFirstName("geller");
		assertEquals(0, actualUsers.size());
	}
	
	@Transactional
	@Test
	public void testGetByLastNameUnique(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser2());		
		List<User> actualUsers = userRepo.findByFirstName("rachel");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByLastNameMultiple(){		
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(ExpectedTestUsers.getUser4());		
		expectedUsers.add(ExpectedTestUsers.getUser1());		
		List<User> actualUsers = userRepo.findByLastName("geller");
		assertEquals(expectedUsers, actualUsers);
	}
	
	@Transactional
	@Test
	public void testGetByLastNameNegative(){
		List<User> actualUsers = userRepo.findByLastName("ross");
		assertEquals(0, actualUsers.size());
	}
	
	
	
	
	
	
	
	
	
}





