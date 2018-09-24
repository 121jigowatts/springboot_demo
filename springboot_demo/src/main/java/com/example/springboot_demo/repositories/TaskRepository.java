package com.example.springboot_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_demo.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
