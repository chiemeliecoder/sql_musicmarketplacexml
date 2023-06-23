package com.laba.solvd.databases.model;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Album {

  private int id;

  private String albumName;

  private Date albumDate;

  private List<Track> tracks;

  private List<Review> reviews;



  //constructor


  public Album() {
  }

  public Album(int id, String albumName, Date albumDate,
      List<Track> tracks, List<Review> reviews) {
    this.id = id;
    this.albumName = albumName;
    this.albumDate = albumDate;
    this.tracks = tracks;
    this.reviews = reviews;
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

  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

//equals and hashcode


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Album)) {
      return false;
    }
    Album album = (Album) o;
    return getId() == album.getId() && getAlbumName().equals(album.getAlbumName()) && getAlbumDate()
        .equals(album.getAlbumDate()) && getTracks().equals(album.getTracks()) && reviews
        .equals(album.reviews);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getAlbumName(), getAlbumDate(), getTracks(), reviews);
  }


  @Override
  public String toString() {
    return "Album{" +
        "id=" + id +
        ", albumName='" + albumName + '\'' +
        ", albumDate=" + albumDate +
        ", tracks=" + tracks +
        ", reviews=" + reviews +
        '}';
  }
}
