package com.challengers.spring.data.mongodb.repository;

import java.util.List;

import com.challengers.spring.data.mongodb.model.Component;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentRepository extends MongoRepository<Component, String> {
  List<Component> findByisCentralApp(boolean isCentralApp);
  List<Component> findBynameContaining(String name);
}
