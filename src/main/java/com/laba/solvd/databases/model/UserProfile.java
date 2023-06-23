package com.laba.solvd.databases.model;

import java.util.Objects;

public class UserProfile {

  private Integer id;

  private String bio;

  private String profileimage;

  private String location;




  public UserProfile() {
  }

  public UserProfile(Integer id, String bio, String profileimage, String location){
    this.id = id;
    this.bio = bio;
    this.profileimage = profileimage;
    this.location = location;

  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserProfile)) {
      return false;
    }
    UserProfile that = (UserProfile) o;
    return getId() == that.getId() && getBio().equals(that.getBio()) && getProfileimage()
        .equals(that.getProfileimage()) && getLocation().equals(that.getLocation());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getBio(), getProfileimage(), getLocation());
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
