package com.laba.solvd.databases.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Purchase {

  private int id;

  private Date purchaseDate;

  private BigDecimal price;



  public Purchase() {
  }


  public Purchase(int id, Date purchaseDate, BigDecimal price) {
    this.id = id;
    this.purchaseDate = purchaseDate;
    this.price = price;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Purchase)) {
      return false;
    }
    Purchase purchase = (Purchase) o;
    return getId() == purchase.getId() && getPurchaseDate().equals(purchase.getPurchaseDate())
        && getPrice().equals(purchase.getPrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getPurchaseDate(), getPrice());
  }

  @Override
  public String toString() {
    return "Purchase{" +
        "id=" + id +
        ", purchaseDate=" + purchaseDate +
        ", price=" + price +
        '}';
  }
}
