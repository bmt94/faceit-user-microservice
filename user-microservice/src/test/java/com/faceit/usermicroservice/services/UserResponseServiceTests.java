package com.faceit.usermicroservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.faceit.usermicroservice.ExpectedTestUsers;
import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.web_entities.UserResponse;

@SpringBootTest
public class UserResponseServiceTests {
	
	@Autowired
	private UserResponseService userResponseService;

	
	@Transactional
	@Test
	public void testUserToResponseList() {
		Iterable<User> users = new ArrayList<User>(Arrays.asList(
				ExpectedTestUsers.getUser1(), ExpectedTestUsers.getUser2() ));
		List<UserResponse> usersResponseActual = userResponseService.UserToResponse(users);
		
		List<UserResponse> usersResponseExpected = new ArrayList<>();
		usersResponseExpected.add(new UserResponse(1, "Ross", "Geller", "the_geller", "ross@live.com", "USA") );
		usersResponseExpected.add(new UserResponse(2, "Rachel", "Green", "rachelg", "rachel@live.com", "Greece") );
		
		assertEquals(usersResponseExpected, usersResponseActual);
	}
	
		
	@Transactional
	@Test
	public void testUserToResponse() {
		UserResponse userResponseActual = userResponseService.UserToResponse(ExpectedTestUsers.getUser1());		
		UserResponse userResponseExpected = new UserResponse(1, "Ross", "Geller", "the_geller", "ross@live.com", "USA");		
		assertEquals(userResponseActual, userResponseExpected);
	}
	
	
	
}
