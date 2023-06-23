package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.WishlistDAO;
import com.laba.solvd.databases.interfacedao.IGenericDAO;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Wishlist;
import com.laba.solvd.databases.service.interfaceservice.IAlbumService;
import com.laba.solvd.databases.service.interfaceservice.IWishlistService;
import java.util.List;
import java.util.stream.Collectors;

public class WishlistImpl implements IWishlistService {

  private final IGenericDAO wishlistDAO;
  private final IAlbumService albumService;

  public WishlistImpl() {
    this.wishlistDAO = new WishlistDAO();
    this.albumService = new AlbumServiceImpl();
  }

  @Override
  public Wishlist create(Wishlist entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Wishlist entity cannot be null");
    }
//    entity.setId(null);
    wishlistDAO.create(entity);


    if(entity.getAlbumList() != null){
      List<Album> albums = entity.getAlbumList().stream()
          .map(album -> albumService.create(album)).collect(
              Collectors.toList());
      entity.setAlbumList(albums);
    }

    return entity;
  }

  @Override
  public List<Wishlist> getAll() {
    return wishlistDAO.getAll();
  }
}
