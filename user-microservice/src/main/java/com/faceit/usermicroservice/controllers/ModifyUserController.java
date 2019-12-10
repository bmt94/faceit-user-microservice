package com.faceit.usermicroservice.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.user_repository.UserRepository;

@RestController
@RequestMapping("/users")
public class ModifyUserController {
	
	@Autowired
	UserRepository userRepo;
	
	@PutMapping(value = "/modify")
	public ResponseEntity<User> modifyUser(@RequestBody User user, HttpServletRequest request){	
//		Optional<User> user = userRepo.findById(userID); 
//		if(user.isPresent()) return ResponseEntity.ok(user.get());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return null;
	}

	
}








