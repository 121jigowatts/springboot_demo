package com.example.springboot_demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_demo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findById(long id);
}
