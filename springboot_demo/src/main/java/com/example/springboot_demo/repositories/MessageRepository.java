package com.example.springboot_demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_demo.domain.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

}