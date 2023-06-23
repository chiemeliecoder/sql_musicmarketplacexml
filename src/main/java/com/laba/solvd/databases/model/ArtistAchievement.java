package com.laba.solvd.databases.model;

import java.sql.Date;
import java.util.Objects;

public class ArtistAchievement {

  private int id;

  private String title;

  private Date awardDate;



  public ArtistAchievement() {
  }

  public ArtistAchievement(int id, String title, Date awardDate) {
    this.id = id;
    this.title = title;
    this.awardDate = awardDate;
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

  public Date getAwardDate() {
    return awardDate;
  }

  public void setAwardDate(Date awardDate) {
    this.awardDate = awardDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtistAchievement)) {
      return false;
    }
    ArtistAchievement that = (ArtistAchievement) o;
    return getId() == that.getId() && getTitle().equals(that.getTitle()) && getAwardDate()
        .equals(that.getAwardDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getAwardDate());
  }

  @Override
  public String toString() {
    return "ArtistAchievement{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", awardDate=" + awardDate +
        '}';
  }
}
