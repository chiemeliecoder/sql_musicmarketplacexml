package com.laba.solvd.databases.model;

import java.util.Objects;

public class Genre {

  private int id;

  private String genreName;

  public Genre() {
  }

  public Genre(int id, String genreName) {
    this.id = id;
    this.genreName = genreName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Genre)) {
      return false;
    }
    Genre genre = (Genre) o;
    return getId() == genre.getId() && getGenreName().equals(genre.getGenreName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getGenreName());
  }

  @Override
  public String toString() {
    return "Genre{" +
        "id=" + id +
        ", genreName='" + genreName + '\'' +
        '}';
  }
}
