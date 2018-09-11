package com.example.springboot_demo;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T> extends Serializable {
	public List<T> getAll();
	public T findById(long id);
	public List<T> findByName(String name);
	
	public List<T> search(String cstr);
}
