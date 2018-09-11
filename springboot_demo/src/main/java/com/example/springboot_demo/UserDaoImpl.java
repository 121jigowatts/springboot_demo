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

	@Override
	public User findById(long id) {
		return (User) entityManager.createQuery("from User where id = " + id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String name) {
		return (List<User>) entityManager.createQuery("from User where name = " + name).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> search(String cstr) {
		List<User> users = null;
		Query query = entityManager.createNamedQuery("findByName").setParameter("cname", "%" + cstr + "%");
		users = query.getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByAge(int min, int max) {
		List<User> users = null;
		Query query = entityManager.createNamedQuery("findByAge").setParameter("min", min).setParameter("max", max);
		users = query.getResultList();
		return users;
	}

}
