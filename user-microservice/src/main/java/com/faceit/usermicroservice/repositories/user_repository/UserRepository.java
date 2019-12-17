package com.faceit.usermicroservice.repositories.user_repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.faceit.usermicroservice.entities.User;

public interface UserRepository  extends CrudRepository<User, Integer>, UserCustomRepository{	
	
	@Query("SELECT e FROM User e WHERE lower( e.firstName) = lower(:firstName) AND  lower( e.lastName) = lower(:lastName) ORDER BY lastName")
	List<User> findByName( @Param("firstName") String firstName, @Param("lastName") String lastName);
	
	@Query("SELECT e FROM User e WHERE lower( e.firstName) = lower(:firstName) ORDER BY lastName")
	List<User> findByFirstName( @Param("firstName") String firstName);
		
	@Query("SELECT e FROM User e WHERE lower( e.lastName) = lower(:lastName) ORDER BY firstName")
	List<User> findByLastName( @Param("lastName") String lastName);

	@Query("SELECT e FROM User e WHERE lower( e.nickname) = lower(:nickname) ORDER BY firstName")
	List<User> findByNickName(String nickname);

	@Query("SELECT e FROM User e WHERE  e.password = :password ORDER BY firstName")
	List<User> findByPassword(String password);

	@Query("SELECT e FROM User e WHERE lower( e.email) = lower(:email) ORDER BY firstName")
	List<User> findByEmail(String email);

	@Query("SELECT e FROM User e WHERE lower( e.country) = lower(:country) ORDER BY firstName")
	List<User> findByCountry(String country);
	
	
}