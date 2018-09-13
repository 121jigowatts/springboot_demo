package com.example.springboot_demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return (List<User>) entityManager.createQuery("from User").getResultList();
	}

	public User get(int num) {
		return (User) entityManager.createQuery("from User where id = " + num).getSingleResult();
	}

	public List<User> search(String cstr){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("name"), cstr));
		List<User> users = null;
		users = (List<User>)entityManager.createQuery(query).getResultList();
		return users;
	}
}
