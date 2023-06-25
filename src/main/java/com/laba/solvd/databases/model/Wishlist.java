package com.laba.solvd.databases.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Wishlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class Wishlist {

  @XmlElement
  private int id;

  @XmlElement
  private String name;

  @XmlElementWrapper(name = "AlbumList")
  @XmlElement(name = "Album")
  @JsonProperty("albums")
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
  public String toString() {
    return "Wishlist{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", albumList=" + albumList +
        '}';
  }
}
