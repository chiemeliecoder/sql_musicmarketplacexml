package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.Artists;
import java.util.List;

public interface IArtistService {

  Artists createArt( Artists entity);
  List<Artists> getAllArtists();

}
