package com.laba.solvd.databases.model;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Artists")
@XmlAccessorType(XmlAccessType.FIELD)
public class Artists {

  @XmlElement
  private int id;

  @XmlElement
  private String artistName;

  @XmlElementWrapper(name = "Albums")
  @XmlElement(name = "Album")
  private List<Album> albums;


  public Artists() {
  }

  public Artists(int id, String artistName,
      List<Album> albums) {
    this.id = id;
    this.artistName = artistName;
    this.albums = albums;
  }

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public List<Album> getAlbums() {
    return albums;
  }

  public void setAlbums(List<Album> albums) {
    this.albums = albums;
  }


  @Override
  public String toString() {
    return "Artists{" +
        "id=" + id +
        ", artistName='" + artistName + '\'' +
        ", albums=" + albums +
        '}';
  }
}
