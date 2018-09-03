package com.example.springboot_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_demo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
