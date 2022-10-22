package com.challengers.spring.data.mongodb.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.challengers.spring.data.mongodb.model.Deployment;
import com.challengers.spring.data.mongodb.repository.DeploymentRepository ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class DeploymentController {
    @Autowired
    DeploymentRepository DeploymentRepository ;

  @GetMapping("/deployments")
  public ResponseEntity<List<Deployment>> getAllDeployments(@RequestParam(required = false) String envname) {
    try {
      List<Deployment> Deployments = new ArrayList<Deployment>();

      if (envname == null)
        DeploymentRepository .findAll().forEach(Deployments::add);
      else
        DeploymentRepository .findByEnvNameContaining(envname).forEach(Deployments::add);

      if (Deployments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(Deployments, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/deployments/{id}")
  public ResponseEntity<Deployment> getDeploymentById(@PathVariable("id") String id) {
    Optional<Deployment> DeploymentData = DeploymentRepository .findById(id);

    if (DeploymentData.isPresent()) {
      return new ResponseEntity<>(DeploymentData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/deployments")
  public ResponseEntity<Deployment> createDeployment(@RequestBody Deployment Deployment) {
    try {
      Deployment _Deployment = DeploymentRepository .save(new Deployment(Deployment.getEnvName(),Deployment.getComponents(),Deployment.getDeployDateTime()));
      return new ResponseEntity<>(_Deployment, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/deployments/{id}")
  public ResponseEntity<Deployment> updateDeployment(@PathVariable("id") String id, @RequestBody Deployment Deployment) {
    Optional<Deployment> DeploymentData = DeploymentRepository .findById(id);

    if (DeploymentData.isPresent()) {
      Deployment _Deployment = DeploymentData.get();
      _Deployment.setEnvName(Deployment.getEnvName());
      
      return new ResponseEntity<>(DeploymentRepository .save(_Deployment), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/deployments/{id}")
  public ResponseEntity<HttpStatus> deleteDeployment(@PathVariable("id") String id) {
    try {
      DeploymentRepository .deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/deployments")
  public ResponseEntity<HttpStatus> deleteAllDeployments() {
    try {
      DeploymentRepository .deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

 
}

