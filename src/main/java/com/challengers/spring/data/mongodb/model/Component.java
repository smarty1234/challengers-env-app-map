package com.challengers.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "components")
public class Component {
  @Id
  private String id;

  private String name; //from UCD data
  private String envname; //from UCD data
  private String description; 
  private String ucdteamname; //from UCD data
  private boolean centralapp;
  private boolean downstreamapp;
  private boolean upstreamapp;

  public Component() {

  }

  public Component(String name, String envname, String description, String ucdteamname, boolean centralapp, boolean downstreamapp, boolean upstreamapp) {
    this.name = name; // available to pick from UCD table
    this.envname = envname; // available to pick from UCD table
    this.description = description; 
    this.centralapp = centralapp; 
    this.downstreamapp = downstreamapp; 
    this.upstreamapp = upstreamapp;
    this.ucdteamname = ucdteamname; //used for setting central app flag = true
    //means when one would provision these using a form they can only see their
    //own apps as central app while the other app will show apps from other teams
    // once selected they apps can be marked upstream or downstream using the checkboxes
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
  public String getEnvName() {
    return envname;
  }

  public void setEnvName(String envname) {
    this.envname = envname;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getUCDTeamName() {
    return ucdteamname;
  }

  public void setUCDTeamName(String ucdteamname) {
    this.ucdteamname = ucdteamname;
  }

  public boolean isCentralapp() {
    return centralapp;
  }

  public void setCentralapp(boolean iscentralapp) {
    this.centralapp = iscentralapp;
  }
  public boolean isDownstreamapp() {
    return downstreamapp;
  }

  public void setDownstreamapp(boolean isdownstreamapp) {
    this.downstreamapp = isdownstreamapp;
  }
  public boolean isUpstreamapp() {
    return upstreamapp;
  }

  public void setUpstreamapp(boolean isupstreamapp) {
    this.upstreamapp = isupstreamapp;
  }
  @Override
  public String toString() {
    return "Component [id=" + id + ", name=" + name + ", envname=" + envname + ", desc=" + description + ", team=" + ucdteamname + ", centralapp=" + centralapp +  ", upstreamapp=" + upstreamapp+ ", downstreamapp=" + downstreamapp+ "]";
  }
}
