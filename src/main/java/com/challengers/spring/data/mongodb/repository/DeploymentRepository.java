package com.challengers.spring.data.mongodb.repository;

import java.util.List;

import com.challengers.spring.data.mongodb.model.Deployment;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeploymentRepository extends MongoRepository<Deployment, String> {

  List<Deployment> findByEnvName(String envname);
  List<Deployment> findByEnvNameContaining(String envname);
}
