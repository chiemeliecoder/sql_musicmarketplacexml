package com.laba.solvd.databases.service;

import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import java.util.List;

public interface IMusicService {

  void performOperation();
  int countArtists();
  int countUsers();
  int countAlbums();
  int countPlaylists();
  int countTracks();
  int countWishlists();

}
