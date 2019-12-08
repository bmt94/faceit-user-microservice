package com.faceit.usermicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.faceit.usermicroservice.entities.User;

public interface UserRepository  extends CrudRepository<User, Integer> {
	
	
	@Query("SELECT e FROM User e WHERE lower( e.firstName) = lower(:firstName) AND  lower( e.lastName) = lower(:lastName)")
	List<User> findByName( @Param("firstName") String firstName, @Param("lastName") String lastName);
	
	@Query("SELECT e FROM User e WHERE lower( e.firstName) = lower(:firstName)")
	List<User> findByFirstName( @Param("firstName") String firstName);
		
	@Query("SELECT e FROM User e WHERE lower( e.lastName) = lower(:lastName)")
	List<User> findByLastName( @Param("lastName") String lastName);
}