package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.PlaylistDAO;
import com.laba.solvd.databases.interfacedao.IGenericDAO;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.service.interfaceservice.IPlaylistService;
import com.laba.solvd.databases.service.interfaceservice.ITrackService;
import java.util.List;
import java.util.stream.Collectors;

public class PlaylistServiceImpl implements IPlaylistService {


  private IGenericDAO playlistDAO;
  private ITrackService trackService;


  public PlaylistServiceImpl() {

    this.playlistDAO = new PlaylistDAO();
    this.trackService = new TrackImpl();
  }


  @Override
  public Playlist create(Playlist entity) {

    if (entity == null) {
      throw new IllegalArgumentException("Playlist entity cannot be null");
    }

    //    entity.setId(null);
    playlistDAO.create(entity);

    if(entity.getTracks() != null){
      List<Track> tracks = entity.getTracks().stream()
          .map(track -> trackService.create(track)).collect(
              Collectors.toList());
      entity.setTracks(tracks);
    }
    return entity;
  }

  @Override
  public List<Playlist> getAll() {

    return playlistDAO.getAll();
  }
}
