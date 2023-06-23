package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.Album;
import java.util.List;

public interface IAlbumService {
  Album create( Album entity);
  List<Album> getAll();

}
