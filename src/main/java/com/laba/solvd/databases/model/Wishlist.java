package com.laba.solvd.databases.model;

import java.util.List;
import java.util.Objects;

public class Wishlist {

  private int id;

  private String name;

  private List<Album> albumList;



  public Wishlist() {
  }

  public Wishlist(int id, String name, List<Album> albumList) {
    this.id = id;
    this.name = name;
    this.albumList = albumList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Album> getAlbumList() {
    return albumList;
  }

  public void setAlbumList(List<Album> albumList) {
    this.albumList = albumList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Wishlist)) {
      return false;
    }
    Wishlist wishlist = (Wishlist) o;
    return getId() == wishlist.getId() && getName().equals(wishlist.getName()) && getAlbumList()
        .equals(wishlist.getAlbumList());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getAlbumList());
  }

  @Override
  public String toString() {
    return "Wishlist{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", albumList=" + albumList +
        '}';
  }
}
