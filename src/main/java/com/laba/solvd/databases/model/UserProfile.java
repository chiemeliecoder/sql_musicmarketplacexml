package com.laba.solvd.databases.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class UserProfile {

  @XmlElement
  private Integer id;

  @XmlElement
  private String bio;

  @XmlElement
  @JsonProperty("Profileimage")
  private String profileimage;

  @XmlElement
  private String location;


  public UserProfile() {
  }

  public UserProfile(Integer id, String bio, String profileimage, String location){
    this.id = id;
    this.bio = bio;
    this.profileimage = profileimage;
    this.location = location;

  }

  @XmlAttribute
  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getProfileimage() {
    return profileimage;
  }

  public void setProfileimage(String profileimage) {
    this.profileimage = profileimage;
  }

  @XmlElement
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }




  @Override
  public String toString() {
    return "UserProfile{" +
        "id=" + id +
        ", bio='" + bio + '\'' +
        ", profileimage='" + profileimage + '\'' +
        ", location='" + location + '\'' +
        '}';
  }
}
