package com.laba.solvd.databases.model;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Playlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class Playlist {

  @XmlElement
  private int id;

  @XmlElement
  private String playlistName;



  public Playlist() {
  }

  public Playlist(int id, String playlistName) {
    this.id = id;
    this.playlistName = playlistName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPlaylistName() {
    return playlistName;
  }

  public void setPlaylistName(String playlistName) {
    this.playlistName = playlistName;
  }


  @Override
  public String toString() {
    return "Playlist{" +
        "id=" + id +
        ", playlistName='" + playlistName + '\'' +
        '}';
  }
}
