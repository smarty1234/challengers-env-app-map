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

  @GetMapping("/components")
  public ResponseEntity<List<Component>> getAllcomponents(@RequestParam(required = false) String name) {
    try {
      List<Component> components = new ArrayList<Component>();

      if (name == null)
        ComponentRepository .findAll().forEach(components::add);
      else
        ComponentRepository .findByNameContaining(name).forEach(components::add);

      if (components.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(components, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/components/{id}")
  public ResponseEntity<Component> getComponentById(@PathVariable("id") String id) {
    Optional<Component> ComponentData = ComponentRepository .findById(id);

    if (ComponentData.isPresent()) {
      return new ResponseEntity<>(ComponentData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/components")
  public ResponseEntity<Component> createComponent(@RequestBody Component component) {
    try {
      Component _Component = ComponentRepository .save(new Component(component.getName(), component.getEnvName(), component.getDescription(), component.getUCDTeamName(), component.isCentralapp(), component.isDownstreamapp(), component.isUpstreamapp()));
      return new ResponseEntity<>(_Component, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/components/{id}")
  public ResponseEntity<Component> updateComponent(@PathVariable("id") String id, @RequestBody Component Component) {
    Optional<Component> ComponentData = ComponentRepository .findById(id);

    if (ComponentData.isPresent()) {
      Component _Component = ComponentData.get();
      _Component.setName(Component.getName());
      _Component.setDescription(Component.getDescription());
      _Component.setUCDTeamName(Component.getUCDTeamName());
      _Component.setCentralapp(Component.isCentralapp());
      _Component.setUpstreamapp(Component.isUpstreamapp());
      _Component.setDownstreamapp(Component.isDownstreamapp());
      return new ResponseEntity<>(ComponentRepository .save(_Component), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/components/{id}")
  public ResponseEntity<HttpStatus> deleteComponent(@PathVariable("id") String id) {
    try {
      ComponentRepository .deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/components")
  public ResponseEntity<HttpStatus> deleteAllcomponents() {
    try {
      ComponentRepository .deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/components/centralapp")
  public ResponseEntity<List<Component>> findBycentralapp() {
    try {
      List<Component> components = ComponentRepository .findByCentralapp(true);

      if (components.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(components, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/components/upstreamapp")
  public ResponseEntity<List<Component>> findByupstreamapp() {
    try {
      List<Component> components = ComponentRepository .findByUpstreamapp(true);

      if (components.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(components, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/components/downstreamapp")
  public ResponseEntity<List<Component>> findBydownstreamapp() {
    try {
      List<Component> components = ComponentRepository .findByDownstreamapp(true);

      if (components.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(components, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
