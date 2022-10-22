package com.challengers.spring.data.mongodb.repository;

import java.util.List;

import com.challengers.spring.data.mongodb.model.Component;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentRepository extends MongoRepository<Component, String> {
  List<Component> findByCentralapp(boolean centralapp);
  List<Component> findByUpstreamapp(boolean upstreamapp);
  List<Component> findByDownstreamapp(boolean downstreamapp);
  List<Component> findByUCDTeamName(String ucdteamname);
  List<Component> findByNameContaining(String name);
  List<Component> findByEnvName(String envname);
}
