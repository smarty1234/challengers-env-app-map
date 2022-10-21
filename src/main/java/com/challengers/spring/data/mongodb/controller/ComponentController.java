package com.challengers.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.challengers.spring.data.mongodb.model.Component;
import com.challengers.spring.data.mongodb.repository.ComponentRepository ;

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
public class ComponentController {

  @Autowired
  ComponentRepository ComponentRepository ;

  @GetMapping("/Components")
  public ResponseEntity<List<Component>> getAllComponents(@RequestParam(required = false) String name) {
    try {
      List<Component> Components = new ArrayList<Component>();

      if (name == null)
        ComponentRepository .findAll().forEach(Components::add);
      else
        ComponentRepository .findBynameContaining(name).forEach(Components::add);

      if (Components.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(Components, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/Components/{id}")
  public ResponseEntity<Component> getComponentById(@PathVariable("id") String id) {
    Optional<Component> ComponentData = ComponentRepository .findById(id);

    if (ComponentData.isPresent()) {
      return new ResponseEntity<>(ComponentData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Components")
  public ResponseEntity<Component> createComponent(@RequestBody Component Component) {
    try {
      Component _Component = ComponentRepository .save(new Component(Component.getName(), Component.getDescription(), false));
      return new ResponseEntity<>(_Component, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/Components/{id}")
  public ResponseEntity<Component> updateComponent(@PathVariable("id") String id, @RequestBody Component Component) {
    Optional<Component> ComponentData = ComponentRepository .findById(id);

    if (ComponentData.isPresent()) {
      Component _Component = ComponentData.get();
      _Component.setName(Component.getName());
      _Component.setDescription(Component.getDescription());
      _Component.setisCentralApp(Component.isisCentralApp());
      return new ResponseEntity<>(ComponentRepository .save(_Component), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Components/{id}")
  public ResponseEntity<HttpStatus> deleteComponent(@PathVariable("id") String id) {
    try {
      ComponentRepository .deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/Components")
  public ResponseEntity<HttpStatus> deleteAllComponents() {
    try {
      ComponentRepository .deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/Components/isCentralApp")
  public ResponseEntity<List<Component>> findByisCentralApp() {
    try {
      List<Component> Components = ComponentRepository .findByisCentralApp(true);

      if (Components.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(Components, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
