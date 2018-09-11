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
		String queryString = "from User where id = :cstr or name like :cname or mail like :cmail";
		Long id = 0L;
		try {
			id = Long.parseLong(cstr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		Query query = entityManager.createQuery(queryString).setParameter("cstr", id)
				.setParameter("cname", "%" + cstr + "%").setParameter("cmail", cstr + "@%");
		users = query.getResultList();
		return users;
	}

}
