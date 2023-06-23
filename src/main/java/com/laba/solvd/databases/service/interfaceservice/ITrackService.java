package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.Track;
import java.util.List;

public interface ITrackService {
  Track create( Track entity);
  List<Track> getAll();

}
