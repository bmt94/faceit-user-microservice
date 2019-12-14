package com.faceit.usermicroservice.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.user_repository.UserRepository;
import com.faceit.usermicroservice.services.UserResponseService;
import com.faceit.usermicroservice.web_entities.UserResponse;

@RestController
@RequestMapping("/users")
public class UserFilteredReadController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserResponseService userResponseService;


	@GetMapping(value = "/list", params= {"firstName","lastName" }, produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<List<UserResponse>> getUsersByName(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, HttpServletRequest request){	
		List<UserResponse> users = userResponseService.UserToResponse(userRepo.findByName(firstName, lastName));
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/list", params="firstName", produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<List<UserResponse>> getUsersByFirstName(@RequestParam(value = "firstName") String firstName, HttpServletRequest request){	
		List<UserResponse> users = userResponseService.UserToResponse( userRepo.findByFirstName(firstName) );
		return ResponseEntity.ok(users) ;
	}

	@GetMapping(value = "/list", params="lastName", produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<List<UserResponse>> getUsersByLastName(@RequestParam(value = "lastName") String lastName, HttpServletRequest request){	
		List<UserResponse> users = userResponseService.UserToResponse( userRepo.findByLastName(lastName) );
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/list", params="nickName", produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<List<UserResponse>> getUsersByNickName(@RequestParam(value = "nickName") String nickName, HttpServletRequest request){	
		List<UserResponse> users = userResponseService.UserToResponse( userRepo.findByNickName(nickName) );
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/list", params="country", produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<List<UserResponse>> getUsersByCountry(@RequestParam(value = "country") String country, HttpServletRequest request){	
		List<UserResponse> users = userResponseService.UserToResponse( userRepo.findByCountry(country) );
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/list", params="email", produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<List<UserResponse>> getUsersByEmail(@RequestParam(value = "email") String email, HttpServletRequest request){	
		List<UserResponse> users = userResponseService.UserToResponse( userRepo.findByEmail(email) );
		return ResponseEntity.ok(users);
	}
	
	
	
}








