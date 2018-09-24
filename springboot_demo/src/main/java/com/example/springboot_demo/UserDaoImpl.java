package com.example.springboot_demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.springboot_demo.domain.User;

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
		int offset = 1;
		int limit = 2;

		List<User> users = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).orderBy(builder.asc(root.get("name")));

		users = (List<User>) entityManager.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
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

	@Override
	public List<User> search(String cstr) {
		List<User> users = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("name"), cstr));

		users = (List<User>) entityManager.createQuery(query).getResultList();
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
