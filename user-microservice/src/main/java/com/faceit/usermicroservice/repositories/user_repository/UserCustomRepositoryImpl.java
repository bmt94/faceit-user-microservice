package com.faceit.usermicroservice.repositories.user_repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.faceit.usermicroservice.entities.User;

public class UserCustomRepositoryImpl implements UserCustomRepository {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void updateUser(User user) {
		if(entityManager.find(User.class, user.getId()) != null) 
			entityManager.merge(user);
	}

}
