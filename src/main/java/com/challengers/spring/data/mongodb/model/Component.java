package com.challengers.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Components")
public class Component {
  @Id
  private String id;

  private String name;
  private String description;
  private boolean isCentralApp;

  public Component() {

  }

  public Component(String name, String description, boolean isCentralApp) {
    this.name = name;
    this.description = description;
    this.isCentralApp = isCentralApp;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isisCentralApp() {
    return isCentralApp;
  }

  public void setisCentralApp(boolean isisCentralApp) {
    this.isCentralApp = isisCentralApp;
  }

  @Override
  public String toString() {
    return "Component [id=" + id + ", name=" + name + ", desc=" + description + ", isCentralApp=" + isCentralApp + "]";
  }
}
