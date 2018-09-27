package com.example.springboot_demo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.springboot_demo.domain.Task;

@Repository
public class TaskDaoImpl implements TaskDao<Task> {
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;

	public TaskDaoImpl() {
		super();
	}

	public TaskDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getAll() {
		return entityManager.createQuery("from Task").getResultList();
	}

	@Override
	public Task findById(long id) {
		return (Task) entityManager.createQuery("from Task where id = " + id).getSingleResult();
	}

}
