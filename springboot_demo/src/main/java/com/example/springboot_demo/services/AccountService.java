package com.example.springboot_demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot_demo.domain.Account;
import com.example.springboot_demo.domain.User;
import com.example.springboot_demo.repositories.UserRepository;

@Service
public class AccountService implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Optional<User> findUserById(String id) {
		return repository.findById(id);
	}

	public User findUserByName(String name) {
		return repository.findByName(name);
	}

	public void saveUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	    
	    repository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			UserDetails details = new Account(user);
			return details;
		}
	}

}
