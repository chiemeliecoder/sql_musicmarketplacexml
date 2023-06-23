package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.UserProfile;
import java.util.List;

public interface IPlaylistService {
  Playlist create(Playlist entity);
  List<Playlist> getAll();

}
