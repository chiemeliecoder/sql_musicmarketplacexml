package com.laba.solvd.databases.model;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class Track {

  private int id;

  private String title;

  private Time duration;

  private List<Purchase> purchase;



  public Track() {
  }

  public Track(int id, String title, Time duration,
      List<Purchase> purchase) {
    this.id = id;
    this.title = title;
    this.duration = duration;
    this.purchase = purchase;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Time getDuration() {
    return duration;
  }

  public void setDuration(Time duration) {
    this.duration = duration;
  }

  public List<Purchase> getPurchase() {
    return purchase;
  }

  public void setPurchase(List<Purchase> purchase) {
    this.purchase = purchase;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Track)) {
      return false;
    }
    Track track = (Track) o;
    return getId() == track.getId() && getTitle().equals(track.getTitle()) && getDuration()
        .equals(track.getDuration()) && purchase.equals(track.purchase);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDuration(), purchase);
  }

  @Override
  public String toString() {
    return "Track{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", duration=" + duration +
        ", purchase=" + purchase +
        '}';
  }
}
