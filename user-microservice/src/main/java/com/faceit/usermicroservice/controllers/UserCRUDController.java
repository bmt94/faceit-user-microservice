package com.faceit.usermicroservice.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.user_repository.UserRepository;
import com.faceit.usermicroservice.services.UserResponseService;
import com.faceit.usermicroservice.web_entities.UserResponse;

@RestController
@RequestMapping("/users")
public class UserCRUDController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserResponseService userResponseService;
	

	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		List<UserResponse> users = userResponseService.UserToResponse(userRepo.findAll() );
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "/view/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUserByID(@PathVariable(value = "userID") int userID){	
		Optional<User> user = userRepo.findById(userID); 
		if(user.isPresent()) return ResponseEntity.ok( userResponseService.UserToResponse(user.get()) );
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestBody User user){	
		try{
			userRepo.save(user);
		}
		catch(ConstraintViolationException e) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "User properties does not meet required specifications");
		}
	}
	
	@Transactional
	@PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void modifyUser(@RequestBody User user){	
		try{
			if(!userRepo.updateUser(user)) throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User does not exist");
		}
		catch(ConstraintViolationException e) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "User properties does not meet required specifications");
		}
		
	}
	
	@DeleteMapping(value = "/remove/{userID}")
	public void deleteUser(@PathVariable(value="userID") int userID){	
		try {
			userRepo.deleteById(userID);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Cannot delete user as the user id does not exist");
		}
		
	}	
	
	
	
	
}








