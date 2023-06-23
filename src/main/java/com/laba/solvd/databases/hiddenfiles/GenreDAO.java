package com.laba.solvd.databases.hiddenfiles;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IGenreDAO;
import com.laba.solvd.databases.model.Genre;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenreDAO implements IGenreDAO {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();


  public void createGenre(Genre genre) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO genre (id, name) VALUES (?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, genre.getId());
      preparedStatement.setString(2, genre.getGenreName());

      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){}

    }catch(SQLException e){
      throw new RuntimeException("unable to create album", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }

  public Genre getGenreById(int id) throws SQLException {

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    Genre genre = new Genre();
    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GENRE WHERE ID=?");

    preparedStatement.setInt(1,id);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()){
      genre.setId(resultSet.getInt("id"));
      genre.setGenreName(resultSet.getString("name"));
    }

    return genre;

  }

  @Override
  public List<Genre> getAllGenres() {
    List<Genre> genre = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GENRE")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Genre genre1 = new Genre();
        genre1.setId(resultSet.getInt("id"));
        genre1.setGenreName(resultSet.getString("name"));



        genre.add(genre1);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return genre;
  }

//  public static void main(String args[]) throws SQLException {
//    Genre genre = new GenreDAO().getGenreById(1);
//    System.out.println("Award ID: " + genre.getId());
//    System.out.println("genreName: " + genre.getGenreName());
//
//  }
}
