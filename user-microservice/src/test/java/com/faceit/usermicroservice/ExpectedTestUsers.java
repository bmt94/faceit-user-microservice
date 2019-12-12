package com.faceit.usermicroservice;

import com.faceit.usermicroservice.entities.User;

public final class ExpectedTestUsers {
	
	private ExpectedTestUsers() {}
	
	
	public static User getUser1() {
		User userExpected = new User();
		userExpected.setId(1);
		userExpected.setCountry("USA");
		userExpected.setEmail("ross@live.com");
		userExpected.setFirstName("Ross");
		userExpected.setLastName("Geller");
		userExpected.setNickname("the_geller");
		userExpected.setPassword("$2y$10$maEUORd2DLNYcbrmN9IlDu.ZI5itTB7AkXrw1wcXJkEkKopI7tXhS");
		return userExpected;
	}
	
	public static User getUser2() {
		User userExpected = new User();
		userExpected.setId(2);
		userExpected.setCountry("Greece");
		userExpected.setEmail("rachel@live.com");
		userExpected.setFirstName("Rachel");
		userExpected.setLastName("Green");
		userExpected.setNickname("rachelg");
		userExpected.setPassword("$2y$10$maEUORd2DLNYcbrmN9IlDu.ZI5itTB7AkXrw1wcXJkEkKopI7tXhS");	
		return userExpected;
	}
	
	public static User getUser3() {
		User userExpected = new User();
		userExpected.setId(3);
		userExpected.setCountry("Italy");
		userExpected.setEmail("joey@live.com");
		userExpected.setFirstName("Joey");
		userExpected.setLastName("Tribiani");
		userExpected.setNickname("joeyt");
		userExpected.setPassword("$2y$10$c8ETCvUvqP1ELKM43rQ.guAK/bn7obUS5M6G7qQgOIOJxjwgnIkf2");
		return userExpected;
	}
	
	public static User getUser4() {
		User userExpected = new User();
		userExpected.setId(4);
		userExpected.setCountry("USA");
		userExpected.setEmail("monica@live.com");
		userExpected.setFirstName("Monica");
		userExpected.setLastName("Geller");
		userExpected.setNickname("the_geller");
		userExpected.setPassword("$2y$10$VwaxizupGHDlr/erTpKUXOsNOllfh0ZESgZhPjzuzWilfJCATP9ly");	
		return userExpected;
	}
	

}
