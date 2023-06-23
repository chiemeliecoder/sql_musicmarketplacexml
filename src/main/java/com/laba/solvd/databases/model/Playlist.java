package com.laba.solvd.databases.model;

import java.util.List;
import java.util.Objects;

public class Playlist {

  private int id;

  private String playlistName;

  private List<Track> tracks;

  public Playlist() {
  }

  public Playlist(int id, String playlistName,
      List<Track> tracks) {
    this.id = id;
    this.playlistName = playlistName;
    this.tracks = tracks;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPlaylistName() {
    return playlistName;
  }

  public void setPlaylistName(String playlistName) {
    this.playlistName = playlistName;
  }


  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Playlist)) {
      return false;
    }
    Playlist playlist = (Playlist) o;
    return getId() == playlist.getId() && getPlaylistName().equals(playlist.getPlaylistName())
        && getTracks().equals(playlist.getTracks());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getPlaylistName(), getTracks());
  }

  @Override
  public String toString() {
    return "Playlist{" +
        "id=" + id +
        ", playlistName='" + playlistName + '\'' +
        ", tracks=" + tracks +
        '}';
  }
}
