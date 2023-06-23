package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.Wishlist;
import java.util.List;

public interface IWishlistService {
  Wishlist create( Wishlist entity);
  List<Wishlist> getAll();

}
