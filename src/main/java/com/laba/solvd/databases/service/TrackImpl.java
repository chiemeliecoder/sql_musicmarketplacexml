package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.TrackDAO;
import com.laba.solvd.databases.interfacedao.IGenericDAO;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.service.interfaceservice.ITrackService;
import java.util.List;

public class TrackImpl implements ITrackService {
  private final IGenericDAO trackDAO;

  public TrackImpl() {
    this.trackDAO = new TrackDAO();
  }


  @Override
  public Track create(Track entity) {
    trackDAO.create(entity);
    return entity;
  }

  @Override
  public List<Track> getAll() {
    return trackDAO.getAll();
  }
}
