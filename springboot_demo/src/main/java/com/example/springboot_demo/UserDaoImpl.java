package com.example.springboot_demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImpl implements UserDao<User> {
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;

	public UserDaoImpl() {
		super();
	}
	
	public UserDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}
	
	@Override
	public List<User> getAll() {
		Query query = entityManager.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		entityManager.close();

		return users;
	}

}
