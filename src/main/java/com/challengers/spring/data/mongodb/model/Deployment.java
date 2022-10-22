package com.challengers.spring.data.mongodb.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;

@EnableRelMongo
public class Deployment {
    @Id
  private String id;
  private String envname;
  @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinProperty(name="components")
  private List<Component> components;
  @JsonbDateFormat("yyyy-MM-dd")
  private Date  deploydatetime; 


public Deployment() {

}

public Deployment(String envname, List<Component> components, Date deploydatetime  ) {
  this.envname = envname; 
  this.components = components;
  this.deploydatetime = deploydatetime;
}
public String getId() {
    return id;
  }

  public String getEnvName() {
    return envname;
  }

 
  public void setEnvName(String envname) {
    this.envname = envname;
  }

  public List<Component>  getComponents() {
    return components;
  }

 
  public void setComponents(List<Component> components) {
    this.components = components;
  }
  public Date getDeployDateTime() {
    return deploydatetime;
  }

 
  public void setDeployDateTime(Date deploydatetime) {
    this.deploydatetime = deploydatetime;
  }
}