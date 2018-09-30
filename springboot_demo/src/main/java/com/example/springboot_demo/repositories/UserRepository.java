package com.example.springboot_demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_demo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByName(String name);

}
