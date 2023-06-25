package com.laba.solvd.databases.model;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

  @XmlElement
  @JsonProperty("UserId")
  private Integer UserId;

  @XmlElement
  private String name;

  @XmlElement
  private String email;

  @XmlElement
  private String password;

  @XmlElement(name = "UserProfile")
  private UserProfile userProfile;

  @XmlElementWrapper(name = "PlaylistList")
  @XmlElement(name = "Playlist")
  private List<Playlist> playlistList;

  @XmlElementWrapper(name = "WishlistList")
  @XmlElement(name = "Wishlist")
  private List<Wishlist> wishlistList;



  public User() {
  }

  public User(Integer id, String name, String email, String password) {
    this.UserId = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public User(Integer id, String name, String email, String password,
      UserProfile userProfile,
      List<Playlist> playlistList,
      List<Wishlist> wishlistList) {
    this.UserId = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.userProfile = userProfile;
    this.playlistList = playlistList;
    this.wishlistList = wishlistList;
  }

  @JsonProperty("Id")
  public Integer getId() {
    return UserId;
  }

  public void setId(Integer id) {
    this.UserId = id;
  }

  @JsonProperty("Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("Email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @JsonProperty("Password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @JsonProperty("UserProfile")
  public UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  @JsonProperty("PlaylistsList")
  public List<Playlist> getPlaylistList() {
    return playlistList;
  }

  public void setPlaylistList(List<Playlist> playlistList) {
    this.playlistList = playlistList;
  }

  @JsonProperty("WishlistsList")
  public List<Wishlist> getWishlistList() {
    return wishlistList;
  }

  public void setWishlistList(List<Wishlist> wishlistList) {
    this.wishlistList = wishlistList;
  }


  @Override
  public String toString() {
    return "User{" +
        "id=" + UserId +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", userProfile=" + userProfile +
        ", playlistList=" + playlistList +
        ", wishlistList=" + wishlistList +
        '}';
  }
}
