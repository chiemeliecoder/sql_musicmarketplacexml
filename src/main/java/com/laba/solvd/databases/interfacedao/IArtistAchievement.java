package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.ArtistAchievement;
import java.util.List;

public interface IArtistAchievement {
  void createArtistAchievement(ArtistAchievement achievement);

  List<ArtistAchievement> getAllArtistAchievements();

}
