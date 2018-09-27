package com.example.springboot_demo;

import java.io.Serializable;
import java.util.List;

import com.example.springboot_demo.domain.Task;

public interface TaskDao<T> extends Serializable {
	public List<Task> getAll();

	public Task findById(long id);
}
