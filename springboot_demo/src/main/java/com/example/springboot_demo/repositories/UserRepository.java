package com.example.springboot_demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot_demo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u from User u order by u.name desc")
	public List<User> findAllOrderByNameDesc();

	@Query("from User where age > :min and age < :max")
	public List<User> findByAge(@Param("min") int min, @Param("max") int max);

	public Optional<User> findById(long id);
}
