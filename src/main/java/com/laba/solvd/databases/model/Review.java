package com.laba.solvd.databases.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Review {

  private int id;

  private BigDecimal ratings;

  private String comments;



  public Review() {
  }

  public Review(int id, BigDecimal ratings, String comments) {
    this.id = id;
    this.ratings = ratings;
    this.comments = comments;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getRatings() {
    return ratings;
  }

  public void setRatings(BigDecimal ratings) {
    this.ratings = ratings;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Review)) {
      return false;
    }
    Review review = (Review) o;
    return getId() == review.getId() && getRatings().equals(review.getRatings()) && getComments()
        .equals(review.getComments());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getRatings(), getComments());
  }

  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", ratings=" + ratings +
        ", comments='" + comments + '\'' +
        '}';
  }
}
