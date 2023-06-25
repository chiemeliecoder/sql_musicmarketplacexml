package com.laba.solvd.databases.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;


@XmlRootElement(name = "Album")
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {

  @XmlElement
  private int id;

  @XmlElement
  private String albumName;

  @XmlElement
  @XmlSchemaType(name = "date")
  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  private Date albumDate;


  //constructor


  public Album() {
  }

  public Album(int id, String albumName, Date albumDate){
    this.id = id;
    this.albumName = albumName;
    this.albumDate = albumDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAlbumName() {
    return albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public Date getAlbumDate() {
    return albumDate;
  }

  public void setAlbumDate(Date albumDate) {
    this.albumDate = albumDate;
  }


  @Override
  public String toString() {
    return "Album{" +
        "id=" + id +
        ", albumName='" + albumName + '\'' +
        ", albumDate=" + albumDate +
        '}';
  }
}
