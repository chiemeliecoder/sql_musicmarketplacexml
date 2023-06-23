package com.laba.solvd.databases.dao;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IGenericDAO;
import com.laba.solvd.databases.model.Genre;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.model.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PlaylistDAO implements IGenericDAO<Playlist> {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
  private static final String DELETE = "DELETE FROM Playlists WHERE id=?";

  public List<Track> getTrack(int trackID) {
    List<Track> tracks = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT tracks.id, tracks.title " +
        "FROM tracks " +
        "JOIN playlist_track ON playlist_track.track_id = tracks.id " +
        "JOIN playlists ON playlists.id = playlist_track.playlist_id " +
        "WHERE tracks.id = ?")){
      preparedStatement.setInt(1, trackID);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Track track = new Track();
        track.setId(resultSet.getInt("id"));
        track.setTitle(resultSet.getString("title"));

        tracks.add(track);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return tracks;
  }

  @Override
  public Playlist getById(int id) throws SQLException {

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    Playlist playlist = new Playlist();
    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists WHERE ID=?");

    preparedStatement.setInt(1,id);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()){
      playlist.setId(resultSet.getInt("id"));
      playlist.setPlaylistName(resultSet.getString("name"));
    }

    return playlist;
  }

  @Override
  public void create(Playlist entity) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("Insert into playlists (id, name) VALUES (?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, entity.getId());
      preparedStatement.setString(2, entity.getPlaylistName());


      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){}

    }catch(SQLException e){
      throw new RuntimeException("unable to create user", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }



  @Override
  public List<Playlist> getAll() {
    List<Playlist> playlists = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Playlist playlist = new Playlist();
        playlist.setId(resultSet.getInt("id"));
        playlist.setPlaylistName(resultSet.getString("name"));

        playlists.add(playlist);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return playlists;
  }



  /**
   * Retrieve an object that was previously persisted to the database using
   *
   * @param id
   */
  @Override
  public Playlist read(int id) {
    return null;
  }

  @Override
  public void update(Playlist entity) {

  }

  @Override
  public void delete(int id) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    if(id <= 0){
      throw new IllegalArgumentException("id value is invalid");
    }

    try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    }catch (SQLException e){
      throw new RuntimeException("unable to delete", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }

//  public static void main(String args[]) throws SQLException {
//
//  }
}
