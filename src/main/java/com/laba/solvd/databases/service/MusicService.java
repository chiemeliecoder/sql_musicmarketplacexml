package com.laba.solvd.databases.service;

import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.Wishlist;
import java.util.List;

public class MusicService implements IMusicService {

  private UserServiceImpl userService;
  private ArtistServiceImpl artistService;
  private AlbumServiceImpl albumService;
  private PlaylistServiceImpl playlistService;
  private TrackImpl trackService;
  private WishlistImpl wishlistService;



  public MusicService() {
    this.userService = new UserServiceImpl();
    this.artistService = new ArtistServiceImpl();
    this.albumService = new AlbumServiceImpl();
    this.trackService = new TrackImpl();
    this.wishlistService = new WishlistImpl();
    this.playlistService = new PlaylistServiceImpl();
  }




  @Override
  public void performOperation() {

    System.out.println("loading musicmarketplace...");

  }


  @Override
  public int countArtists() {
    List<Artists> allArtists = artistService.getAllArtists();
    return allArtists.size();
  }

  @Override
  public int countUsers() {
    List<User> allUsers = userService.getAllUsers();
    return allUsers.size();
  }

  @Override
  public int countAlbums() {
    List<Album> allAlbum = albumService.getAll();
    return allAlbum.size();
  }
  @Override
  public int countPlaylists() {
    List<Playlist> allPlaylist = playlistService.getAll();
    return allPlaylist.size();
  }

  @Override
  public int countTracks() {
    List<Track> allTrack = trackService.getAll();
    return allTrack.size();
  }

  @Override
  public int countWishlists() {
    List<Wishlist> allWishlist= wishlistService.getAll();
    return allWishlist.size();
  }


}


