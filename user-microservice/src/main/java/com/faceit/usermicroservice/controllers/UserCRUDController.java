package com.faceit.usermicroservice.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.user_repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserCRUDController {
	
	@Autowired
	private UserRepository userRepo;
	

	
	@GetMapping(value = "/list")
	public ResponseEntity<Iterable<User>> getAllUsers(HttpServletRequest request){
		Iterable<User> users = userRepo.findAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "/view/{userID}")
	public ResponseEntity<User> getUserByID(@PathVariable(value = "userID") int userID, HttpServletRequest request){	
		Optional<User> user = userRepo.findById(userID); 
		if(user.isPresent()) return ResponseEntity.ok(user.get());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PostMapping(value = "/add")
	public void addUser(@RequestBody User user, HttpServletRequest request){	
		userRepo.save(user);
	}
	
	@PutMapping(value = "/modify")
	public void modifyUser(@RequestBody User user, HttpServletRequest request){	
		userRepo.updateUser(user);
	}
	
	@DeleteMapping(value = "/remove")
	public void deleteUser(@RequestBody User user, HttpServletRequest request){	
		userRepo.delete(user);
	}	
	
	
	
	
}








